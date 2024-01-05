package freevoice.features.forum.services;

import freevoice.features.forum.models.ForumCommentCreateDto;
import freevoice.features.forum.models.ForumCommentDto;

import java.util.List;

public interface ForumCommentService {
    ForumCommentDto create(ForumCommentCreateDto forumComment);
    ForumCommentDto getById(Long commentId);
    List<ForumCommentDto> getAllByPostId(Long postId);
    void delete(ForumCommentDto comment) throws Exception;
}
