package estudos.spring_secutiry_auth.controller;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import estudos.spring_secutiry_auth.repository.UserRepository;
import estudos.spring_secutiry_auth.repository.entity.UserEntity;

@SpringBootTest
@AutoConfigureMockMvc
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    public void setUp() {
        // Limpa o repositório para garantir um estado limpo antes de cada teste
        userRepository.deleteAll();

        // Adiciona um usuário de teste
        UserEntity user = UserEntity.builder()
            .username("user")
            .password(passwordEncoder.encode("password"))
            .roles(Set.of("ROLE_USER"))
            .build();

        // Adiciona um administrador de teste
        UserEntity admin = UserEntity.builder()
            .username("admin")
            .password(passwordEncoder.encode("adminpass"))
            .roles(Set.of("ROLE_ADMIN"))
            .build();

        userRepository.save(user);
        userRepository.save(admin);
    }

    @Test
    void testLoginWithValidUser() throws Exception {
        mockMvc.perform(post("/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"username\":\"user\", \"password\":\"password\"}"))
            .andExpect(status().isOk())
            .andExpect(content().string("Login successful"));
    }

    @Test
    void testLoginWithInvalidUser() throws Exception {
        mockMvc.perform(post("/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"username\":\"user\", \"password\":\"wrongpassword\"}"))
            .andExpect(status().isUnauthorized())
            .andExpect(content().string(containsString("Login failed")));
    }

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    void testLogout() throws Exception {
        mockMvc.perform(post("/auth/logout"))
            .andExpect(status().isOk())
            .andExpect(content().string("Logout successful"));
    }
}
