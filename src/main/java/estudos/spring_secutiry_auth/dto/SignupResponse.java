package estudos.spring_secutiry_auth.dto;

import java.util.Set;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Builder
public class SignupResponse {
    private Integer id;
    private String email;
    private String fullName;
    private String username;
    private Set<String> roles;
}
