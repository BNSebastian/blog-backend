package freevoice.features.videos.persistence;

import freevoice.features.videos.models.VideoCommentUpdateDto;
import freevoice.features.videos.models.VideoCommentDto;

import java.util.List;

public interface VideoCommentService {
    VideoCommentDto createComment(VideoCommentDto videoComment);
    VideoCommentDto getComment(Long commentId);
    List<VideoCommentDto> getAllComments(String videoName);
    VideoCommentDto updateComment(VideoCommentUpdateDto request);
    void deleteComment(Long commentId);
}
