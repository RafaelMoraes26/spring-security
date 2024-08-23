package estudos.spring_secutiry_auth.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import estudos.spring_secutiry_auth.dto.*;
import estudos.spring_secutiry_auth.mapper.AuthMapper;
import estudos.spring_secutiry_auth.repository.entity.UserEntity;
import estudos.spring_secutiry_auth.service.AuthService;
import estudos.spring_secutiry_auth.service.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/auth")
@Slf4j
public class AuthController {

    private final JwtService jwtService;

    private final AuthService authService;

    private final AuthMapper authMapper;

    public AuthController(JwtService jwtService, AuthService authService, AuthMapper authMapper) {
        this.jwtService = jwtService;
        this.authService = authService;
        this.authMapper = authMapper;
    }

    @PostMapping("/signup")
    public ResponseEntity<SignupResponse> register(@RequestBody SignupRequest request) {
        UserEntity registeredUser = authService.signup(request);

        return ResponseEntity.ok(authMapper.toResponse(registeredUser));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginRequest request) {
        log.info("/login: {} - {}", request.getUsername(), request.getPassword());
        UserEntity authenticatedUser = authService.authenticate(request);
        log.info("userEntity: {}", authenticatedUser);
        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginResponse loginResponse = LoginResponse.builder()
            .token(jwtToken)
            .expiresIn(jwtService.getExpirationTime())
            .build();

        return ResponseEntity.ok(loginResponse);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request, HttpServletResponse response) {
        // Obtém o objeto de autenticação do contexto de segurança atual.
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        // Verifica se o usuário está autenticado (ou seja, se o objeto de autenticação não é nulo).
        if (auth != null) {
            // Se o usuário estiver autenticado, realiza o logout.
            // O logout limpa o contexto de segurança, invalida a sessão HTTP e limpa quaisquer cookies de autenticação.
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }

        // Retorna uma resposta indicando que o logout foi bem-sucedido.
        return ResponseEntity.ok("Logout successful");
    }

}