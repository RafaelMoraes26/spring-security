package estudos.spring_secutiry_auth.service;

import java.util.List;

import org.springframework.stereotype.Service;

import estudos.spring_secutiry_auth.repository.UserRepository;
import estudos.spring_secutiry_auth.repository.entity.UserEntity;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserEntity> allUsers() {
        return userRepository.findAll();
    }
}
