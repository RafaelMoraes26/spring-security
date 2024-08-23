package estudos.spring_secutiry_auth.repository;

import estudos.spring_secutiry_auth.repository.entity.UserEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test") // Usa o perfil de teste para garantir que o banco de dados H2 em memória seja usado
class UserEntityRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    private UserEntity userEntity;

    @BeforeEach
    void setUp() {
        userEntity = new UserEntity();
        userEntity.setUsername("testuser");
        userEntity.setPassword("password123");
        userEntity.setFullName("test User");
        userEntity.setEmail("test@email.com");
        userEntity.setRoles(Set.of("ROLE_USER"));
        userRepository.save(userEntity); // Salva o usuário no banco de dados em memória antes de cada teste
    }

    @Test
    void whenFindByUsername_thenReturnUser() {
        Optional<UserEntity> getUser = userRepository.findByUsername(userEntity.getUsername());
        if (getUser.isPresent()) {
            UserEntity foundUser = getUser.get();
            assertThat(foundUser).isNotNull();
            assertThat(foundUser.getUsername()).isEqualTo(userEntity.getUsername());
            assertThat(foundUser.getPassword()).isEqualTo(userEntity.getPassword());
        }
    }

    @Test
    void whenUserDoesNotExist_thenReturnNull() {
        Optional<UserEntity> foundUser = userRepository.findByUsername("nonexistentuser");
        assertThat(foundUser).isEmpty();
    }

    @Test
    void whenSaveUser_thenUserIsPersisted() {
        UserEntity newUserEntity = new UserEntity();
        newUserEntity.setUsername("newuser");
        newUserEntity.setPassword("newpassword");
        newUserEntity.setFullName("new User");
        newUserEntity.setEmail("new@email.com");
        newUserEntity.setRoles(Set.of("ROLE_ADMIN"));

        userRepository.save(newUserEntity);

        Optional<UserEntity> foundUser = userRepository.findByUsername("newuser");
        if(foundUser.isPresent()) {
            assertThat(foundUser).isNotNull();
            assertThat(foundUser.get().getUsername()).isEqualTo("newuser");
            assertThat(foundUser.get().getPassword()).isEqualTo("newpassword");
            assertThat(foundUser.get().getRoles()).containsExactlyInAnyOrder("ROLE_ADMIN");
        }
    }
}
