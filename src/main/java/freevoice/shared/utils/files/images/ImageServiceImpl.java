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
import java.util.Objects;

@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ImageRepository imageRepository;

    @Override
    public Image saveImage(MultipartFile file) throws IOException {
        String filename = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        Image newImage = new Image(file.getBytes(), filename);
        return imageRepository.save(newImage);
    }

    @Override
    public boolean setProfileImage(Long userId, MultipartFile file) throws IOException {
        UserEntity foundUser = userRepository.findById(userId)
                                             .orElseThrow();
        Image savedImage = saveImage(file);

        foundUser.setProfileImageId(savedImage.getId());

        userRepository.save(foundUser);

        return true;
    }

    @Override
    @Transactional
    public ByteArrayResource getProfileImage(String userEmail) {
        UserEntity foundUser = userRepository.findByEmail(userEmail)
                                             .orElseThrow();

        Image foundImage = imageRepository.findById(foundUser.getProfileImageId())
                                          .orElseThrow(() -> new UserNotFoundException(userEmail));

        return new ByteArrayResource(foundImage.getFile());
    }
}
