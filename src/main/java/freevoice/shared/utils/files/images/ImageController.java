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
import java.util.List;

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
    public ResponseEntity<Boolean> setProfileImage(
            @PathVariable("userId") Long userId,
            @PathVariable("imageId") Long imageId) throws IOException {
        System.out.println("ImageController::setProfileImage - trying to set profile image");
        return new ResponseEntity<>(
                imageService.setProfileImage(userId, imageId),
                HttpStatus.OK);
    }

    /**
     * Saves the uploaded image to the file system.
     *
     * @param file the uploaded image file
     * @return a response indicating whether the operation was successful
     * @throws IOException if there was an error saving the image
     */
    @PostMapping(URLS.saveUserProfileImage)
    public ResponseEntity<String> saveUserProfileImage(
            @RequestParam("file") MultipartFile file) throws IOException {
        imageService.saveImage(file);
        return new ResponseEntity<String>("image saved", HttpStatus.OK);
    }

    /**
     * Returns the profile image for the specified user.
     *
     * @param userEmail the email of the user
     * @return the profile image as a PNG file
     */
    @GetMapping(URLS.getUserProfileImage)
    public ResponseEntity<ByteArrayResource> getProfileImage(
            @PathVariable("userEmail") String userEmail) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.IMAGE_PNG)
                .body(imageService.getProfileImage(userEmail));
    }

    /**
     * Returns the image data for the specified image ID.
     *
     * @param id the ID of the image
     * @return the image data as a PNG file
     */
    @GetMapping(URLS.getProfileImageById)
    public ResponseEntity<ByteArrayResource> getById(@PathVariable("id") Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.IMAGE_PNG)
                .body(imageService.getById(id));
    }

    /**
     * Returns a list of all image IDs.
     *
     * @return a list of all image IDs
     */
    @GetMapping(URLS.getProfileImageCount)
    public ResponseEntity<List<Long>> getPrimaryKeys() {
        return ResponseEntity.ok(imageService.getPrimaryKeys());
    }
}
