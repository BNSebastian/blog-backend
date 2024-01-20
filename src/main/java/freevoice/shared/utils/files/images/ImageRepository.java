package freevoice.shared.utils.files.images;

import freevoice.shared.utils.files.images.models.Image;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface ImageRepository extends JpaRepository<Image, Long>{
}
