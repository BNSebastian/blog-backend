package freevoice.features.services.implementations;

import freevoice.core.user.UserEntity;
import freevoice.core.user.UserRepository;
import freevoice.features.exceptions.CommentNotFoundException;
import freevoice.features.exceptions.VideoNotFoundException;
import freevoice.features.models.Video;
import freevoice.features.models.VideoComment;
import freevoice.features.models.dtos.UpdateVideoCommentDto;
import freevoice.features.models.dtos.VideoCommentDto;
import freevoice.features.repositories.VideoCommentRepository;
import freevoice.features.repositories.VideoRepository;
import freevoice.features.services.VideoCommentService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class VideoCommentServiceImpl implements VideoCommentService {
    @Autowired
    private VideoRepository videoRepository;

    @Autowired
    private VideoCommentRepository videoCommentRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public VideoCommentDto createComment(VideoCommentDto videoComment) {
        Video video = videoRepository.findByName(videoComment.getVideoName())
                                     .orElseThrow(() ->new VideoNotFoundException(videoComment.getVideoName()));

        UserEntity user = userRepository.findByEmail(videoComment.getUserEmail()).orElseThrow();

        VideoComment newComment = VideoComment.builder()
                .parentId(videoComment.getParentId())
                                              .video(video)
                                              .user(user)
                                              .content(videoComment.getContent())
                                              .createdOn(LocalDateTime.now())
                                              .build();

        VideoComment savedComment = videoCommentRepository.save(newComment);

        return VideoCommentDto.mapToDto(savedComment);
    }

    @Override
    public VideoCommentDto getComment(Long commentId) {
        VideoComment comment = videoCommentRepository.findById(commentId)
                                                     .orElseThrow(() -> new CommentNotFoundException(commentId.toString()));
        return VideoCommentDto.mapToDto(comment);
    }

    @Override
    public List<VideoCommentDto> getAllComments(String videoName) {
        Video video = videoRepository.findByName(videoName)
                                     .orElseThrow(() ->new VideoNotFoundException(videoName));

        return video.getVideoComments()
                    .stream()
                    .map(VideoCommentDto::mapToDto)
                    .collect(Collectors.toList());
    }

    @Override
    public VideoCommentDto updateComment(UpdateVideoCommentDto request) {
        // get comment
        VideoComment comment = videoCommentRepository.findById(request.getId())
                                                     .orElseThrow(() -> new CommentNotFoundException(request.getId().toString()));
        // update comment
        comment.setContent(request.getContent());
        // save comment
        VideoComment response = videoCommentRepository.save(comment);
        // return comment
        return VideoCommentDto.mapToDto(response);
    }

    @Override
    public void deleteComment(Long commentId) {
        // get comment
        VideoComment comment = videoCommentRepository.findById(commentId)
                                                     .orElseThrow(() -> new CommentNotFoundException(commentId.toString()));
        videoCommentRepository.delete(comment);
    }
}