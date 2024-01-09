package freevoice.core.user;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
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

    @Override
    public void changePassword(ChangePasswordRequest request, Principal connectedUser) {

        var user = (UserEntity) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();

        // check if the current password is correct
        if (!passwordEncoder.matches(request.getCurrentPassword(), user.getPassword())) {
            throw new IllegalStateException("Wrong password");
        }
        // check if the two new passwords are the same
        if (!request.getNewPassword().equals(request.getConfirmationPassword())) {
            throw new IllegalStateException("Password are not the same");
        }

        // update the password
        user.setPassword(passwordEncoder.encode(request.getNewPassword()));

        // save the new password
        userRepository.save(user);
    }

    @Override
    public UserEntity uploadProfileImage(UserEntity userEntity, MultipartFile file) throws IOException {
        UserEntity foundUser = userRepository.findById(userEntity.getId()).orElseThrow();
        foundUser.setProfileImage(file.getBytes());
        return userRepository.save(foundUser);
    }


}
