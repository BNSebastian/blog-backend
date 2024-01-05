package freevoice.features.forum.services;

import freevoice.core.user.UserEntity;
import freevoice.core.user.UserRepository;
import freevoice.features.forum.models.ForumComment;
import freevoice.features.forum.models.ForumCommentCreateDto;
import freevoice.features.forum.models.ForumCommentDto;
import freevoice.features.forum.models.ForumPost;
import freevoice.features.forum.repositories.ForumCommentRepository;
import freevoice.features.forum.repositories.ForumPostRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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
        ForumPost foundPost = postRepository.findById(postId).orElseThrow();
        List<ForumComment> comments = foundPost.getComments();
        return comments.stream()
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
}
