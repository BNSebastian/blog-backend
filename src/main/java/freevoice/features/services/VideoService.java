package freevoice.features.services;

import freevoice.features.models.VideoEntity;

import java.util.List;

public interface VideoService {
    VideoEntity createPost(VideoEntity videoEntity);
    public VideoEntity getById(Long id);
    public List<VideoEntity> getAll();
    public VideoEntity updatePost(VideoEntity videos , Long id);
    public void deleteVideos(Long id);
}
