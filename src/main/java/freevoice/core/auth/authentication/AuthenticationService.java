package freevoice.core.auth.authentication;

import freevoice.core.auth.registration.RegistrationService;
import freevoice.core.config.JwtService;
import freevoice.core.auth.registration.email.EmailSender;
import freevoice.core.user.Role;
import freevoice.core.user.UserEntity;
import freevoice.core.user.UserRepository;
import freevoice.core.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
        private final UserRepository userRepository;
        private final UserService userService;
        private final RegistrationService registrationService;
        private final EmailSender emailSender;
        private final PasswordEncoder passwordEncoder;
        private final JwtService jwtService;
        private final AuthenticationManager authenticationManager;

        public AuthenticationResponse register(RegistrationRequest request) {

                if (userRepository.findByEmail(request.getEmail()).isPresent()) {
                        throw new RuntimeException();
                }

                var user = UserEntity.builder()
                                .firstname(request.getFirstname())
                                .lastname(request.getLastname())
                                .email(request.getEmail())
                                .password(passwordEncoder.encode(request.getPassword()))
                                .role(Role.USER)
                                .profileImageId(1L)
                                .build();

                // String emailToken = userService.signUpUser(user);
                // String emailLink = URLS.confirmUser + emailToken;
                // emailSender.send(request.getEmail(),
                // registrationService.buildEmail(request.getFirstname(),
                // emailLink));

                var savedUser = userRepository.save(user);

                var jwtToken = jwtService.generateToken(user);

                return AuthenticationResponse.builder()
                                .id(user.getId())
                                .email(user.getEmail())
                                .firstname(user.getFirstname())
                                .lastname(user.getLastname())
                                .token(jwtToken)
                                .build();
        }

        public AuthenticationResponse authenticate(AuthenticationRequest request) {
                authenticationManager.authenticate(
                                new UsernamePasswordAuthenticationToken(
                                                request.getEmail(),
                                                request.getPassword()));

                var user = userRepository.findByEmail(request.getEmail())
                                .orElseThrow();

                var jwtToken = jwtService.generateToken(user);

                return AuthenticationResponse.builder()
                                .id(user.getId())
                                .email(user.getEmail())
                                .firstname(user.getFirstname())
                                .lastname(user.getLastname())
                                // .profileImage(new ByteArrayResource(user.getProfileImage()))
                                .token(jwtToken)
                                .build();
        }
}