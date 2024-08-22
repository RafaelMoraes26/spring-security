package estudos.spring_secutiry_auth.repository;

import estudos.spring_secutiry_auth.repository.entity.UserEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test") // Usa o perfil de teste para garantir que o banco de dados H2 em memória seja usado
public class UserEntityRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    private UserEntity userEntity;

    @BeforeEach
    void setUp() {
        userEntity = new UserEntity();
        userEntity.setUsername("testuser");
        userEntity.setPassword("password123");
        userEntity.setRoles(Set.of("ROLE_USER"));
        userRepository.save(userEntity); // Salva o usuário no banco de dados em memória antes de cada teste
    }

    @Test
    void whenFindByUsername_thenReturnUser() {
        UserEntity foundUser = userRepository.findByUsername(userEntity.getUsername());
        assertThat(foundUser).isNotNull();
        assertThat(foundUser.getUsername()).isEqualTo(userEntity.getUsername());
        assertThat(foundUser.getPassword()).isEqualTo(userEntity.getPassword());
    }

    @Test
    void whenUserDoesNotExist_thenReturnNull() {
        UserEntity foundUser = userRepository.findByUsername("nonexistentuser");
        assertThat(foundUser).isNull();
    }

    @Test
    void whenSaveUser_thenUserIsPersisted() {
        UserEntity newUserEntity = new UserEntity();
        newUserEntity.setUsername("newuser");
        newUserEntity.setPassword("newpassword");
        newUserEntity.setRoles(Set.of("ROLE_ADMIN"));

        userRepository.save(newUserEntity);

        UserEntity foundUser = userRepository.findByUsername("newuser");
        assertThat(foundUser).isNotNull();
        assertThat(foundUser.getUsername()).isEqualTo("newuser");
        assertThat(foundUser.getPassword()).isEqualTo("newpassword");
        assertThat(foundUser.getRoles()).containsExactlyInAnyOrder("ROLE_ADMIN");
    }
}
