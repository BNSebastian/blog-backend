package freevoice.core.controller;

import freevoice.core.model.AuthenticationRequest;
import freevoice.core.model.AuthenticationResponse;
import freevoice.core.model.RegisterRequest;
import freevoice.core.service.AuthenticationService;
import freevoice.core.service.JwtService;
import freevoice.core.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authService;
    private final JwtService jwtService;
    private final UserServiceImpl userService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(authService.authenticate(request));
    }

    @GetMapping("/isAdmin")
    public ResponseEntity<Boolean> isAdmin(@RequestBody Map<String, Long> body) {
        Long userId = body.get("userId");
        return ResponseEntity.ok(userService.checkIfAdmin(userId));
    }

    @GetMapping("/getUsername")
    public ResponseEntity<String> getUsername(@RequestBody String authToken, UserDetails user) {
        jwtService.isTokenValid(authToken, user);
        return ResponseEntity.ok(jwtService.extractUsername(authToken));
    }

}
