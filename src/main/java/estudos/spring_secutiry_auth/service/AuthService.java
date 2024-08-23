package estudos.spring_secutiry_auth.service;

import java.util.Set;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import estudos.spring_secutiry_auth.dto.LoginRequest;
import estudos.spring_secutiry_auth.dto.SignupRequest;
import estudos.spring_secutiry_auth.repository.UserRepository;
import estudos.spring_secutiry_auth.repository.entity.UserEntity;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AuthService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    public AuthService(
        UserRepository userRepository,
        AuthenticationManager authenticationManager,
        PasswordEncoder passwordEncoder
    ) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserEntity signup(SignupRequest input) {
        return userRepository.save(
            UserEntity.builder()
                .fullName(input.getFullName())
                .email(input.getEmail())
                .password(passwordEncoder.encode(input.getPassword()))
                .username(input.getUsername())
                .roles(Set.of("ROLE_USER"))
                .build());
    }

    public UserEntity authenticate(LoginRequest request) {
        try {
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    request.getUsername(),
                    request.getPassword()
                )
            );

            return userRepository.findByUsername(request.getUsername()).orElseThrow();
        } catch (Exception e) {
            log.info("Authentication failed: {}", e.getMessage());
            throw e;
        }
    }
}
