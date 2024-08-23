package estudos.spring_secutiry_auth.mapper;

import org.springframework.stereotype.Component;

import estudos.spring_secutiry_auth.dto.SignupResponse;
import estudos.spring_secutiry_auth.repository.entity.UserEntity;

@Component
public class AuthMapper {

    public SignupResponse toResponse(UserEntity userEntity) {
        return SignupResponse.builder()
            .id(userEntity.getId())
            .email(userEntity.getEmail())
            .fullName(userEntity.getFullName())
            .username(userEntity.getUsername())
            .roles(userEntity.getRoles())
            .build();
    }
}
