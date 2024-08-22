package estudos.spring_secutiry_auth.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    void testUserEndpointWithUserRole() throws Exception {
        mockMvc.perform(get("/user"))
            .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "user", roles = {"NOT_USER"})
    void testUserEndpointWithWrongUserRole() throws Exception {
        mockMvc.perform(get("/user"))
            .andExpect(status().isForbidden());
    }
}
