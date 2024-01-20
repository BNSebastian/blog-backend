package freevoice.shared.utils.files.images;

import freevoice.shared.constants.URLS;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping(URLS.image)
@RequiredArgsConstructor
public class ImageController {

    @Autowired
    private ImageService imageService;

    @PostMapping(URLS.setUserProfileImage)
    public ResponseEntity<Boolean> setProfileImage(@RequestParam("file") MultipartFile file,
                                                   @PathVariable("userId") Long userId
    ) throws IOException {
        return new ResponseEntity<>(imageService.setProfileImage(userId, file), HttpStatus.OK);
    }

    @GetMapping(URLS.getUserProfileImage)
    public ResponseEntity<ByteArrayResource> getProfileImage(@PathVariable("userEmail") String userEmail) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.IMAGE_PNG)
                .body(imageService.getProfileImage(userEmail));
    }
}
