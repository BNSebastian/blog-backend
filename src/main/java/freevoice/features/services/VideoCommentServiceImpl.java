package freevoice.features.services;

import freevoice.core.user.UserEntity;
import freevoice.core.user.UserRepository;
import freevoice.features.models.Video;
import freevoice.features.models.VideoComment;
import freevoice.features.models.dtos.VideoCommentDto;
import freevoice.features.repositories.VideoCommentRepository;
import freevoice.features.repositories.VideoRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public VideoCommentDto create(VideoCommentDto videoComment) {
        Video video = videoRepository.findByName(videoComment.getVideoName()).orElseThrow();
        UserEntity user = userRepository.findByEmail(videoComment.getUserEmail()).orElseThrow();

        VideoComment newComment = VideoComment.builder()
                                              .video(video)
                                              .user(user)
                                              .content(videoComment.getContent())
                                              .build();

        VideoComment savedComment = videoCommentRepository.save(newComment);

        return VideoCommentDto.mapToDto(savedComment);
    }

    @Override
    public List<VideoCommentDto> getAllByVideoName(String videoName) {
        Video video = videoRepository.findByName(videoName).orElseThrow();
        return video.getVideoComments()
                    .stream()
                    .map(VideoCommentDto::mapToDto)
                    .collect(Collectors.toList());
    }
}
