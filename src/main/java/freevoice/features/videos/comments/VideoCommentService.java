package freevoice.features.videos.comments;

import freevoice.features.videos.comments.models.VideoCommentUpdateDto;
import freevoice.features.videos.comments.models.VideoCommentDto;

import java.util.List;

public interface VideoCommentService {
    VideoCommentDto createComment(VideoCommentDto videoComment);
    VideoCommentDto getComment(Long commentId);
    List<VideoCommentDto> getAllComments(String videoName);
    VideoCommentDto updateComment(VideoCommentUpdateDto request);
    void deleteComment(Long commentId);
}
