package freevoice.core.user;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<UserEntity> update(UserEntity userEntity, Long id);
    void delete(UserEntity userEntity);
    Optional<List<UserEntity>> getAll();     // mine
    Boolean checkIfAdmin(Long id);    // mine
    void changePassword(ChangePasswordRequest request, Principal connectedUser);
    boolean setProfileImage(Long userId, MultipartFile file) throws IOException;
    ByteArrayResource getProfileImage(Long userId);
}
