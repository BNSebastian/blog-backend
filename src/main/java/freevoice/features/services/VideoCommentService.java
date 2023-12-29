package freevoice.features.services;

import freevoice.features.models.dtos.UpdateVideoCommentDto;
import freevoice.features.models.dtos.VideoCommentDto;

import java.util.List;

public interface VideoCommentService {
    VideoCommentDto createComment(VideoCommentDto videoComment);
    VideoCommentDto getComment(Long commentId);
    List<VideoCommentDto> getAllComments(String videoName);
    VideoCommentDto updateComment(UpdateVideoCommentDto request);
    void deleteComment(Long commentId);
}
