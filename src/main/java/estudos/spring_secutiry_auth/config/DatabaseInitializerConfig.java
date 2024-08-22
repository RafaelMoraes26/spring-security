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

            UserEntity user = new UserEntity();
            user.setUsername("user");
            user.setPassword(passwordEncoder.encode("password"));
            user.setRoles(Set.of("ROLE_USER"));
            userRepository.save(user);

            UserEntity admin = new UserEntity();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin"));
            admin.setRoles(Set.of("ROLE_USER", "ROLE_ADMIN"));
            userRepository.save(admin);
        };
    }
}
