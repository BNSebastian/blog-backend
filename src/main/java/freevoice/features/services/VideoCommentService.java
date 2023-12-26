package freevoice.features.services;

import freevoice.features.models.dtos.VideoCommentDto;

import java.util.List;

public interface VideoCommentService {
    VideoCommentDto create(VideoCommentDto videoComment);
    List<VideoCommentDto> getAllByVideoName(String videoName);
}
