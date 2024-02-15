package freevoice.shared.utils.files.images;

import freevoice.core.user.UserEntity;
import freevoice.core.user.UserRepository;
import freevoice.shared.utils.files.images.models.Image;
import lombok.extern.slf4j.Slf4j;
import freevoice.shared.utils.files.images.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ImageRepository imageRepository;

    /**
     * Saves an image uploaded by a user.
     *
     * @param file the uploaded image file
     * @return the saved image
     * @throws IOException if there was an error reading the uploaded image file
     */
    @Override
    public Image saveImage(MultipartFile file) throws IOException {
        String filename = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        Image newImage = new Image(file.getBytes(), filename);
        return imageRepository.save(newImage);
    }

    /**
     * Sets the profile image for the specified user.
     *
     * @param userId  the ID of the user
     * @param imageId the ID of the image to set as the profile image
     * @return true if the profile image was set, false otherwise
     * @throws IOException if there was an error reading the image file
     */
    @Override
    public boolean setProfileImage(Long userId, Long imageId) throws IOException {
        UserEntity foundUser = userRepository
                .findById(userId)
                .orElseThrow();

        foundUser.setProfileImageId(imageId);

        userRepository.save(foundUser);

        return true;
    }

    /**
     * Returns the profile image for the specified user, or null if no image is set.
     *
     * @param userEmail the email of the user
     * @return the profile image, or null if no image is set
     */
    @SuppressWarnings("null")
    @Override
    @Transactional
    public ByteArrayResource getProfileImage(String userEmail) {
        Optional<UserEntity> userOptional = userRepository.findByEmail(userEmail);
        if (userOptional.isPresent()) {
            UserEntity foundUser = userOptional.get();
            Optional<Image> imageOptional = imageRepository.findById(foundUser.getProfileImageId());
            if (imageOptional.isPresent()) {
                Image foundImage = imageOptional.get();
                log.info("getProfileImage:: profile image found for user: {}", userEmail);
                return new ByteArrayResource(foundImage.getFile());
            } else {
                log.warn("getProfileImage:: profile image not found for user: {}", userEmail);
            }
        } else {
            log.warn("getProfileImage: user with email: {} not found", userEmail);
        }
        return null;
    }

    @Override
    public List<Long> getPrimaryKeys() {
        List<Long> result = new ArrayList<>();

        imageRepository
                .findAll()
                .forEach(current -> result.add(current.getId()));

        System.out.println(result.toString());
        return result;
    }

    @Override
    public ByteArrayResource getById(Long id) {

        Image foundImage = imageRepository
                .findById(id)
                .orElseThrow();

        return new ByteArrayResource(foundImage.getFile());
    }
}