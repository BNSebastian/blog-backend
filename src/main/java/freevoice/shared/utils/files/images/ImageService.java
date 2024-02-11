package freevoice.shared.utils.files.images;

import freevoice.shared.utils.files.images.models.Image;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ImageService {
    Image saveImage(MultipartFile file) throws IOException;

    boolean setProfileImage(Long userId, Long imageId) throws IOException;

    ByteArrayResource getProfileImage(String userEmail);

    ByteArrayResource getById(Long id);

    List<Long> getPrimaryKeys();
}
