package freevoice.features.forum.comments;

import freevoice.features.forum.comments.models.ForumComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ForumCommentRepository extends JpaRepository<ForumComment, Long> {
}
