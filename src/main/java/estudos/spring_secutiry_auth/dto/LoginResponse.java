package estudos.spring_secutiry_auth.dto;

import lombok.*;

@Getter @Setter
@Builder
public class LoginResponse {

    private String token;
    private long expiresIn;
}
