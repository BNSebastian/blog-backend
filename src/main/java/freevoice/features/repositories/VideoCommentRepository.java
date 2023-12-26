package freevoice.features.repositories;

import freevoice.features.models.VideoComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface VideoCommentRepository extends JpaRepository<VideoComment, UUID> {
}
