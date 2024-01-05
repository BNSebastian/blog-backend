package freevoice.features.chat.repositories;

import freevoice.features.chat.models.ChatComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatCommentRepository extends JpaRepository<ChatComment, Long> {
}
