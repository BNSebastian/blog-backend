package freevoice.features.videos.videos;

import freevoice.features.forum.posts.models.ForumPost;
import freevoice.features.forum.posts.models.ForumPostDto;
import freevoice.features.videos.videos.exceptions.VideoAlreadyExistsException;
import freevoice.features.videos.videos.models.Video;
import freevoice.features.videos.videos.models.VideoDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
@Slf4j
@Service
@AllArgsConstructor
public class VideoServiceImpl implements VideoService {

    private VideoRepository videoRepository;

    @Override
    @Transactional
    public Video uploadVideo(MultipartFile file, String name) throws IOException {
        if (videoRepository.existsByName(name)) {
            throw new VideoAlreadyExistsException();
        }
        Video newVid = new Video(name, file.getBytes());
        return videoRepository.save(newVid);
    }

    @Override
    public Video changeVideo(MultipartFile file, String name) throws IOException {
        Video foundVideo = videoRepository.findByName(name).orElseThrow();
        foundVideo.setData(file.getBytes());
        return videoRepository.save(foundVideo);
    }

    @Override
    public String changeVideoName(String videoName, String newName) {
        Video foundVideo = videoRepository.findByName(videoName).orElseThrow();
        foundVideo.setName(newName);
        Video savedVideo = videoRepository.save(foundVideo);
        return savedVideo.getName();
    }

    @Override
    @Transactional(readOnly = true)
    public Video getVideo(String name) {
        return videoRepository.findByName(name).orElseThrow();
    }

    @Override
    @Transactional(readOnly = true)
    public List<String> getAllVideoNames() {
        return videoRepository.getAllEntryNames();
    }

    @Override
    public Integer getSize() {
        return videoRepository.getAllEntryNames().size();
    }

    @Override
    public List<String> getPage(int pageIndex, int pageSize) {
        List<String> entries = videoRepository.getAllEntryNames();
        int entrySize = entries.size();
        int left = pageIndex * pageSize;
        int right = left + pageSize;

        if (entrySize == 0) {
            log.warn("no entries present");
            return null;
        } else if (entrySize < left) {
            log.warn("initial index is out of bounds");
            return null;
        }

        if (entrySize < right) {
            right = entrySize;
        }

        List<String> output = new ArrayList<>();

        while (left < right) {
            output.add(entries.get(left));
            left++;
        }

        return output;
    }

    @Override
    public List<VideoDto> getAllVideos() {
        return videoRepository.findAll().stream().map(VideoDto::mapToDto).collect(Collectors.toList());
    }

    @Override
    public VideoDto getVideoDto(String name) {
        Video foundVideo = videoRepository.findByName(name).orElseThrow();
        return VideoDto.mapToDto(foundVideo);
    }

    @Override
    public String setVideoDescription(String videoName, String description) {
        Video foundVideo = videoRepository.findByName(videoName).orElseThrow();
        foundVideo.setDescription(description);
        videoRepository.save(foundVideo);
        return description;
    }

    @Override
    public String getVideoDescription(String videoName) {
        return videoRepository.getVideoDescriptionByName(videoName);
    }

    @Override
    public boolean deleteVideo(String videoName) {
        Video foundVideo = videoRepository.findByName(videoName).orElseThrow();
        videoRepository.delete(foundVideo);
        return true;
    }

}