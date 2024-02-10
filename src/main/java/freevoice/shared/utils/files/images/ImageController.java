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

    /**
     * Sets the user's profile image.
     *
     * @param file   the uploaded image file
     * @param userId the ID of the user
     * @return a response indicating whether the operation was successful
     * @throws IOException if there was an error saving the image
     */
    @PostMapping(URLS.setUserProfileImage)
    public ResponseEntity<Boolean> setProfileImage(@RequestParam("file") MultipartFile file,
            @PathVariable("userId") Long userId) throws IOException {
        return new ResponseEntity<>(imageService.setProfileImage(userId, file), HttpStatus.OK);
    }

    /**
     * Returns the profile image for the specified user.
     *
     * @param userEmail the email of the user
     * @return the profile image as a PNG file
     */
    @GetMapping(URLS.getUserProfileImage)
    public ResponseEntity<ByteArrayResource> getProfileImage(@PathVariable("userEmail") String userEmail) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.IMAGE_PNG)
                .body(imageService.getProfileImage(userEmail));
    }
}
