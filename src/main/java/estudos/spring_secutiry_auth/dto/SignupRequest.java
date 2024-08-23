package estudos.spring_secutiry_auth.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Builder
public class SignupRequest {
    private String email;
    private String password;
    private String fullName;
    private String username;
}
