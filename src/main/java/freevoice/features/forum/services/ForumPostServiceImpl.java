package freevoice.features.forum.services;

import freevoice.core.user.UserEntity;
import freevoice.core.user.UserRepository;
import freevoice.features.forum.models.ForumComment;
import freevoice.features.forum.models.ForumPost;
import freevoice.features.forum.models.ForumPostCreateDto;
import freevoice.features.forum.models.ForumPostDto;
import freevoice.features.forum.repositories.ForumCommentRepository;
import freevoice.features.forum.repositories.ForumPostRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
                                                .content(forumPost.getInitialCommentContent())
                                                .userEntity(user)
                                                .createdOn(LocalDateTime.now())
                                                .build();

        ForumComment savedComment = commentRepository.save(firstComment);

//        List<ForumComment> singleCommentList = Collections.singletonList(savedComment);
//
//        savedPost.setComments(singleCommentList);

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

    @Override
    public Long incrementViewCount(Long postId, String userEmail) {
        ForumPost foundPost = postRepository.findById(postId).orElseThrow();

        List<String> viewers = foundPost.getViewerList();

        if (viewers == null) {
            viewers = new ArrayList<>();
        }

        if (!viewers.contains(userEmail)) {
            viewers.add(userEmail);
        }

        foundPost.setViewerList(viewers);

        postRepository.save(foundPost);

        return (long) foundPost.getViewerList().size();
    }

    @Override
    public Long getViewCount(Long postId) {
        ForumPost foundPost = postRepository.findById(postId).orElseThrow();
        if (foundPost.getViewerList() == null) {
            return 0L;
        } else {
            return (long) foundPost.getViewerList().size();
        }
    }

    @Override
    public boolean pinPost(Long postId) {
        ForumPost foundPost = postRepository.findById(postId).orElseThrow();
        foundPost.setPinned(!foundPost.isPinned());
        postRepository.save(foundPost);
        return foundPost.isPinned();
    }
}
