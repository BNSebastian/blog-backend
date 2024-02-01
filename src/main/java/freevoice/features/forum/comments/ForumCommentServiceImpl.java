package freevoice.features.forum.comments;

import freevoice.core.user.UserEntity;
import freevoice.core.user.UserRepository;
import freevoice.features.forum.comments.models.ForumComment;
import freevoice.features.forum.comments.models.ForumCommentCreateDto;
import freevoice.features.forum.comments.models.ForumCommentDto;
import freevoice.features.forum.posts.models.ForumPost;
import freevoice.features.forum.posts.ForumPostRepository;
import freevoice.features.forum.posts.models.ForumPostDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class ForumCommentServiceImpl implements ForumCommentService {
    @Autowired
    private ForumPostRepository postRepository;

    @Autowired
    private ForumCommentRepository commentRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public ForumCommentDto create(ForumCommentCreateDto forumComment) {
        UserEntity user = userRepository.findByEmail(forumComment.getUserEmail())
                                        .orElseThrow();

        ForumPost post = postRepository.findById(forumComment.getPostId())
                                       .orElseThrow();

        ForumComment newComment = ForumComment.builder()
                                              .post(post)
                                              .content(forumComment.getContent())
                                              .userEntity(user)
                                              .createdOn(LocalDateTime.now())
                                              .build();

        ForumComment savedComment = commentRepository.save(newComment);

        return ForumCommentDto.mapToDto(savedComment);
    }

    @Override
    public ForumCommentDto getById(Long commentId) {
        ForumComment foundComment = commentRepository.findById(commentId).orElseThrow();
        return ForumCommentDto.mapToDto(foundComment);
    }

    @Override
    public List<ForumCommentDto> getAllByPostId(Long postId) {
        System.out.println(postId);
        ForumPost foundPost = postRepository.findById(postId).orElseThrow();
        List<ForumComment> comments = foundPost.getComments();
        return comments.stream()
                       .map(ForumCommentDto::mapToDto)
                       .collect(Collectors.toList());
    }

    @Override
    public Integer getSize(Long postId) {
        ForumPost foundPost = postRepository.findById(postId).orElseThrow();
        return foundPost.getComments().size();
    }

    @Override
    public List<ForumCommentDto> getPage(
            int postId,
            int pageIndex,
            int pageSize
    ) {
        ForumPost foundPost = postRepository.findById((long)postId).orElseThrow();
        List<ForumComment> entries = foundPost.getComments();

        int entrySize = entries.size();
        int left = pageIndex * pageSize;
        int right = left + pageSize;

        if (entrySize == 0) {
            log.warn("no entries present");
            return null;
        } else if (entrySize < left) {
            log.warn("initial index is out of bounds");
            return null;
        }

        if (entrySize < right) {
            right = entrySize;
        }

        List<ForumComment> output = new ArrayList<>();
        while (left < right) {
            output.add(entries.get(left));
            left++;
        }
        return output
                .stream()
                .map(ForumCommentDto::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(ForumCommentDto comment) throws Exception {
        ForumComment foundComment = commentRepository
                .findById(comment.getId())
                .orElseThrow();

        if (!foundComment.getUserEntity().getEmail().equals(comment.getUserEmail())) {
            throw new Exception();
        }

        commentRepository.delete(foundComment);
    }

    @Override
    public Long likeComment(Long id, String userEmail) {
        ForumComment foundComment = commentRepository
                .findById(id)
                .orElseThrow();

        List<String> likes = foundComment.getLikes();

        if (likes == null) {
            likes = new ArrayList<>();
        }

        if (!likes.contains(userEmail)) {
            likes.add(userEmail);
        } else {
            likes.remove(userEmail);
        }

        foundComment.setLikes(likes);
        commentRepository.save(foundComment);
        return (long) likes.size();
    }

    @Override
    public Long dislikeComment(Long id, String userEmail) {
        ForumComment foundComment = commentRepository
                .findById(id)
                .orElseThrow();

        List<String> dislikes = foundComment.getDislikes();

        if (dislikes == null) {
            dislikes = new ArrayList<>();
        }

        if (!dislikes.contains(userEmail)) {
            dislikes.add(userEmail);
        } else {
            dislikes.remove(userEmail);
        }

        foundComment.setDislikes(dislikes);
        commentRepository.save(foundComment);
        return (long) dislikes.size();
    }
}
