package freevoice.features.videos.videos;

import freevoice.features.videos.videos.models.Video;
import freevoice.features.videos.videos.models.VideoDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface VideoService {
    Video getVideo(String name);
    Video uploadVideo(MultipartFile file, String name) throws IOException;
    Video changeVideo(MultipartFile file, String name) throws IOException;
    String changeVideoName(String videoName, String newName);
    List<String> getAllVideoNames();
    List<VideoDto> getAllVideos();
    VideoDto getVideoDto(String name);
    String setVideoDescription(String videoName, String description);
    String getVideoDescription(String videoName);
    boolean deleteVideo(String videoName);
}