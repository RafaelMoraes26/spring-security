package estudos.spring_secutiry_auth.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import estudos.spring_secutiry_auth.repository.UserRepository;
import estudos.spring_secutiry_auth.repository.entity.UserEntity;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        // Converte os papéis (roles) do usuário em uma lista de GrantedAuthority.
        // O map primeiro verifica se a role já começa com "ROLE_", caso não comece, adiciona o prefixo "ROLE_".
        // Depois converte cada role em um SimpleGrantedAuthority.
        List<GrantedAuthority> authorities = user.getRoles().stream()
            .map(role -> role.startsWith("ROLE_") ? role : "ROLE_" + role)
            .map(SimpleGrantedAuthority::new)
            .collect(Collectors.toList());

        // Retorna um objeto UserDetails com as informações do usuário autenticado.
        // Usa o nome de usuário, a senha (que deve estar criptografada), e a lista de autoridades (roles) atribuídas.
        return org.springframework.security.core.userdetails.User
            .withUsername(username)  // Define o nome de usuário.
            .password(user.getPassword())  // Define a senha do usuário.
            .authorities(authorities)  // Define as autoridades (roles) do usuário.
            .build();  // Constrói o objeto UserDetails.
    }
}

