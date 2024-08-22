package estudos.spring_secutiry_auth.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.bind.annotation.*;

import estudos.spring_secutiry_auth.dto.LoginRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;

    public AuthController(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        this.authenticationManager = authenticationConfiguration.getAuthenticationManager();
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest, HttpServletRequest request) {
        try {
            // Autentica o usuário com base nas credenciais fornecidas (nome de usuário e senha).
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
            );

            // Armazena o objeto de autenticação no contexto de segurança atual.
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // Obtém a sessão HTTP associada à requisição. Se uma sessão não existir, uma nova será criada.
            HttpSession session = request.getSession();

            // Salva explicitamente o contexto de segurança na sessão HTTP, garantindo que ele seja reutilizado em requisições subsequentes.
            session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext());

            // Retorna uma resposta indicando que o login foi bem-sucedido.
            return ResponseEntity.ok("Login successful");
        } catch (Exception e) {
            // Em caso de falha na autenticação, retorna uma resposta com status 401 (Unauthorized) e a mensagem de erro correspondente.
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login failed: " + e.getMessage());
        }
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