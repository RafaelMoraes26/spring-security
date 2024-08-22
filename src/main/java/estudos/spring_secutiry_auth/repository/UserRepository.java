package estudos.spring_secutiry_auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import estudos.spring_secutiry_auth.repository.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, String> {
    UserEntity findByUsername(String username);
}
