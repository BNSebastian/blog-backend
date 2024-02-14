package freevoice.core.user;

import freevoice.core.user.Role;
import freevoice.core.auth.registration.token.ConfirmationToken;
import freevoice.core.auth.registration.token.ConfirmationTokenService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ConfirmationTokenService confirmationTokenService;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder,
            ConfirmationTokenService confirmationTokenService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.confirmationTokenService = confirmationTokenService;
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

    /**
     * Returns a list of all users in the system.
     *
     * @return a list of all users in the system
     */
    @Override
    public Optional<List<UserEntity>> getAll() {
        List<UserEntity> users = userRepository.findAll();
        return Optional.of(users);
    }

    /**
     * Returns whether the user with the specified ID has the admin role.
     *
     * @param id the ID of the user
     * @return true if the user has the admin role, false otherwise
     */
    @Override
    public Boolean checkIfAdmin(Long id) {
        Optional<UserEntity> userOptional = userRepository.findById(id);

        return userOptional.map(user -> {
            var role = user.getRole();
            log.info("checkIfAdmin:: user with id: {} has role: {}", id, role);
            return role.equals(Role.ADMIN);
        }).orElseGet(() -> {
            log.info("checkIfAdmin:: user with id: {} wasn't found", id);
            return false;
        });
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
    public UserEntity loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(String.format(email)));
    }

    @Override
    public String signUpUser(UserEntity appUser) {
        boolean userExists = userRepository
                .findByEmail(appUser.getEmail())
                .isPresent();

        if (userExists) {
            // TODO check of attributes are the same and
            // TODO if email not confirmed send confirmation email.

            throw new IllegalStateException("email already taken");
        }

        String encodedPassword = passwordEncoder
                .encode(appUser.getPassword());

        appUser.setPassword(encodedPassword);

        userRepository.save(appUser);

        String token = UUID.randomUUID().toString();

        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                appUser);

        confirmationTokenService.saveConfirmationToken(
                confirmationToken);

        // TODO: SEND EMAIL

        return token;
    }

    @Override
    public int enableAppUser(String email) {
        return userRepository.enableAppUser(email);
    }
}
