package freevoice.shared.utils.files.repositories;

import freevoice.shared.utils.files.entities.Image;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface ImageRepository extends JpaRepository<Image, Long>{
}
