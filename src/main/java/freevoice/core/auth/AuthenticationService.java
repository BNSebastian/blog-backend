package freevoice.core.auth;

import freevoice.core.config.JwtService;
import freevoice.core.user.Role;
import freevoice.core.user.UserEntity;
import freevoice.core.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {

        if (repository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException();
        }

        var user = UserEntity.builder()
                             .firstname(request.getFirstname())
                             .lastname(request.getLastname())
                             .email(request.getEmail())
                             .password(passwordEncoder.encode(request.getPassword()))
                             .role(Role.USER)
                             .build();

        var savedUser = repository.save(user);

        var jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                                     .id(user.getId())
                                     .email(user.getEmail())
                                     .firstname(user.getFirstname())
                                     .lastname(user.getLastname())
                                     //.profileImage(new ByteArrayResource(user.getProfileImage()))
                                     .token(jwtToken)
                                     .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        var user = repository.findByEmail(request.getEmail())
                             .orElseThrow();

        var jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                                     .id(user.getId())
                                     .email(user.getEmail())
                                     .firstname(user.getFirstname())
                                     .lastname(user.getLastname())
                                     //.profileImage(new ByteArrayResource(user.getProfileImage()))
                                     .token(jwtToken)
                                     .build();
    }
}