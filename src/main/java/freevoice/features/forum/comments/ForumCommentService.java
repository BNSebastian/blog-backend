package freevoice.features.forum.comments;

import freevoice.features.forum.comments.models.ForumCommentCreateDto;
import freevoice.features.forum.comments.models.ForumCommentDto;

import java.util.List;

public interface ForumCommentService {
    ForumCommentDto create(ForumCommentCreateDto forumComment);
    ForumCommentDto getById(Long commentId);
    List<ForumCommentDto> getAllByPostId(Long postId);
    void delete(ForumCommentDto comment) throws Exception;
    Long likeComment(Long id, String userEmail);
    Long dislikeComment(Long id, String userEmail);
}
