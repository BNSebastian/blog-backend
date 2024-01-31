package freevoice.features.forum.posts;

import freevoice.features.forum.posts.models.ForumPostCreateDto;
import freevoice.features.forum.posts.models.ForumPostDto;

import java.util.List;

public interface ForumPostService {
    ForumPostDto create(ForumPostCreateDto forumPost);
    ForumPostDto getById(Long postId);
    List<ForumPostDto> getAll();
    Integer getSize();
    List<ForumPostDto> getPage(int pageIndex, int pageSize);
    void deletePost(Long postId, String userEmail) throws Exception;
    Long incrementViewCount(Long postId, String userEmail);
    Long getViewCount(Long postId);
    boolean pinPost(Long postId);
}
