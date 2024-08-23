package estudos.spring_secutiry_auth.config;

import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import estudos.spring_secutiry_auth.repository.UserRepository;
import estudos.spring_secutiry_auth.repository.entity.UserEntity;

@Configuration
public class DatabaseInitializerConfig {

    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository) {
        return args -> {
            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

            userRepository.save(UserEntity.builder()
                .username("user")
                .password(passwordEncoder.encode("password"))
                .fullName("user")
                .email("user@email.com")
                .roles(Set.of("ROLE_USER"))
                .build());

            userRepository.save(UserEntity.builder()
                .username("admin")
                .password(passwordEncoder.encode("admin"))
                .fullName("admin")
                .email("admin@email.com")
                .roles(Set.of("ROLE_USER", "ROLE_ADMIN"))
                .build());
        };
    }
}
