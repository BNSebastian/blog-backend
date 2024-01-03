package freevoice.features.videos.persistence;

import freevoice.features.videos.models.VideoComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoCommentRepository extends JpaRepository<VideoComment, Long> {
}
