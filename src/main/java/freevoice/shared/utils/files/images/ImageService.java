package freevoice.shared.utils.files.images;

import freevoice.shared.utils.files.images.models.Image;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageService {
    Image saveImage(MultipartFile file) throws IOException;
    boolean setProfileImage(Long userId, MultipartFile file) throws IOException;
    ByteArrayResource getProfileImage(String userEmail);
}
