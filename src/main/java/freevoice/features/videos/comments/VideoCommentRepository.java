package freevoice.features.videos.comments;

import freevoice.features.videos.comments.models.VideoComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoCommentRepository extends JpaRepository<VideoComment, Long> {
}
