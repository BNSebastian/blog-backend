package freevoice.core.user;

import freevoice.shared.URLS;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.Objects;

@RestController
@RequestMapping(URLS.user)
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @PatchMapping
    public ResponseEntity<?> changePassword(@RequestBody ChangePasswordRequest request, Principal connectedUser) {
        service.changePassword(request, connectedUser);
        return ResponseEntity.ok().build();
    }

    @PostMapping(URLS.setUserProfileImage)
    public ResponseEntity<Boolean> setProfileImage(@RequestParam("file") MultipartFile request, @PathVariable("userId") Long userId) throws IOException {
        String filename = StringUtils.cleanPath(Objects.requireNonNull(request.getOriginalFilename()));
        return new ResponseEntity<>(service.setProfileImage(userId, request), HttpStatus.OK);
    }

    @PostMapping(URLS.getUserProfileImage)
    public ResponseEntity<ByteArrayResource> getProfileImage(@PathVariable("userId") Long userId) {
        return new ResponseEntity<>(service.getProfileImage(userId), HttpStatus.OK);
    }
}