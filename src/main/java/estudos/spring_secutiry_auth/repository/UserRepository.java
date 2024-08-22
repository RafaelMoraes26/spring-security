package estudos.spring_secutiry_auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import estudos.spring_secutiry_auth.repository.entity.User;

public interface UserRepository extends JpaRepository<User, String> {
    User findByUsername(String username);
}
