package estudos.spring_secutiry_auth.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import estudos.spring_secutiry_auth.repository.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, String> {
    Optional<UserEntity> findByUsername(String username);
}
