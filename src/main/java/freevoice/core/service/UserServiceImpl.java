package freevoice.core.service;

import freevoice.core.model.UserEntity;
import freevoice.core.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<UserEntity> update(UserEntity userEntity, Long id) {
        Optional<UserEntity> currentUser = userRepository.findById(id);
        currentUser = Optional.ofNullable(userEntity);
        return currentUser;
    }

    @Override
    public void delete(UserEntity userEntity) {
        userRepository.delete(userEntity);
    }

    // mine
    @Override
    public Optional<List<UserEntity>> getAll() {
        List<UserEntity> users = userRepository.findAll();
        return Optional.of(users);
    }
    // mine
    @Override
    public Boolean checkIfAdmin(Long id) {
        UserEntity currentUser = userRepository.findById(id).orElseThrow();
        String role = currentUser.getRole().toString();
        System.out.println("--- current user role is " + role);
        return role.equals("ADMIN");
    }
}
