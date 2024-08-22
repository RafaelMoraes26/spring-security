package estudos.spring_secutiry_auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            // Configura as autorizações de requisições HTTP.
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/auth/login").permitAll()  // Permite acesso sem autenticação à rota de login.
                .requestMatchers("/auth/logout").permitAll()  // Permite acesso sem autenticação à rota de logout.
                .requestMatchers("/public/**").permitAll()  // Permite acesso sem autenticação a todas as rotas que começam com /public/.
                .requestMatchers("/user/**").hasAnyRole("USER", "ADMIN")  // Requer que o usuário tenha a role "USER" ou "ADMIN" para acessar qualquer rota que comece com /user/.
                .anyRequest().permitAll()  // Permite acesso a todas as outras rotas sem necessidade de autenticação.
            )
            // Desabilita a proteção CSRF, que é usada para prevenir ataques Cross-Site Request Forgery.
            .csrf(AbstractHttpConfigurer::disable)
            // Desabilita o formulário de login padrão do Spring Security.
            .formLogin(AbstractHttpConfigurer::disable)
            // Desabilita o mecanismo de logout padrão do Spring Security.
            .logout(AbstractHttpConfigurer::disable)
            // Configura a gestão de sessões.
            .sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.ALWAYS)  // Garante que uma nova sessão seja sempre criada após o login.
            )
            // Configura o contexto de segurança.
            .securityContext(securityContext -> securityContext
                .requireExplicitSave(true)  // Requer que o contexto de segurança seja explicitamente salvo na sessão.
            )
            // Adiciona um filtro de logging customizado antes do filtro de autenticação por nome de usuário e senha do Spring Security.
            .addFilterBefore(new LoggingFilter(), UsernamePasswordAuthenticationFilter.class);

        // Retorna a cadeia de filtros configurada.
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
