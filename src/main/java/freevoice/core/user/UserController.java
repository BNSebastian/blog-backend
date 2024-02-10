package freevoice.core.user;

import freevoice.shared.constants.URLS;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;

@RestController
@RequestMapping(URLS.user)
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @PatchMapping
    public ResponseEntity<?> changePassword(
            @RequestBody ChangePasswordRequest request,
            Principal connectedUser) {
        service.changePassword(request, connectedUser);
        return ResponseEntity.ok().build();
    }
}