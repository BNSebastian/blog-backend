package freevoice.shared.utils.files.images;

import freevoice.core.user.UserEntity;
import freevoice.core.user.UserRepository;
import freevoice.shared.utils.files.images.models.Image;
import freevoice.shared.utils.files.images.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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
     * Returns the profile image for the specified user.
     *
     * @param userEmail the email of the user
     * @return the profile image as a byte array
     * @throws UserNotFoundException if the user could not be found
     */
    @Override
    @Transactional
    public ByteArrayResource getProfileImage(String userEmail) {
        UserEntity foundUser = userRepository.findByEmail(userEmail)
                .orElseThrow();

        Image foundImage = imageRepository.findById(foundUser.getProfileImageId())
                .orElseThrow(() -> new UserNotFoundException(userEmail));

        return new ByteArrayResource(foundImage.getFile());
    }

    /**
     * Returns all profile images as a list of byte arrays.
     *
     * @return a list of byte arrays containing the profile images
     */
    @Override
    public List<ByteArrayResource> getAllProfileImages() {
        List<Image> images = imageRepository.findAll();

        return images
                .stream()
                .map(current -> new ByteArrayResource(current.getFile()))
                .collect(Collectors.toList());
    }
}