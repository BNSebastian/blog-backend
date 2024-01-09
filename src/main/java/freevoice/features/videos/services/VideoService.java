package freevoice.features.videos.services;

import freevoice.features.videos.models.Video;
import freevoice.features.videos.models.VideoDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface VideoService {
    Video getVideo(String name);
    Video uploadVideo(MultipartFile file, String name) throws IOException;
    List<String> getAllVideoNames();
    String setVideoDescription(String videoName, String description);
    String getVideoDescription(String videoName);
    boolean deleteVideo(String videoName);
}