package freevoice.features.forum.services;

import freevoice.core.user.UserEntity;
import freevoice.core.user.UserRepository;
import freevoice.features.forum.models.*;
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
public class ForumPostServiceImpl implements ForumPostService {
    @Autowired
    private ForumPostRepository postRepository;

    @Autowired
    private ForumCommentRepository commentRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public ForumPostDto create(ForumPostCreateDto forumPost) {
        UserEntity user = userRepository.findByEmail(forumPost.getUserEmail())
                .orElseThrow();

        ForumPost newPost = ForumPost.builder()
                .name(forumPost.getName())
                .userEntity(user)
                .createdOn(LocalDateTime.now())
                .build();

        ForumPost savedPost = postRepository.save(newPost);

        ForumComment firstComment = ForumComment.builder()
                .post(savedPost)
                .content(forumPost.getInitialComment().getContent())
                .userEntity(user)
                .createdOn(LocalDateTime.now())
                .build();

        ForumComment savedComment = commentRepository.save(firstComment);

        List<ForumComment> existingComments = savedPost.getComments();
        existingComments.add(savedComment);
        savedPost.setComments(existingComments);

        ForumPost savedPostWithInitialComment = postRepository.save(savedPost);

        return ForumPostDto.mapToDto(savedPostWithInitialComment);
    }

    @Override
    public ForumPostDto getById(Long postId) {
        ForumPost foundPost = postRepository.findById(postId).orElseThrow();
        return ForumPostDto.mapToDto(foundPost);
    }

    @Override
    public List<ForumPostDto> getAll() {
        List<ForumPost> foundPosts = postRepository.findAll();
        return foundPosts.stream()
                .map(ForumPostDto::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deletePost(Long postId, String userEmail) throws Exception {
        ForumPost foundPost = postRepository.findById(postId).orElseThrow();

        if (!foundPost.getUserEntity().getEmail().equals(userEmail)) {
            throw new Exception();
        }

        postRepository.delete(foundPost);
    }
}
