package freevoice.features.repositories;

import freevoice.features.models.ChatComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatCommentRepository extends JpaRepository<ChatComment, Long> {
}
