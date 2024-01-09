package freevoice.features.forum.services;

import freevoice.features.forum.models.ForumCommentCreateDto;
import freevoice.features.forum.models.ForumPostCreateDto;
import freevoice.features.forum.models.ForumPostDto;

import java.util.List;

public interface ForumPostService {
    ForumPostDto create(ForumPostCreateDto forumPost);
    ForumPostDto getById(Long postId);
    List<ForumPostDto> getAll();
    void deletePost(Long postId, String userEmail) throws Exception;
}