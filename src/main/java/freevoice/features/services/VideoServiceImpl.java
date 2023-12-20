package freevoice.features.services;

import freevoice.features.exceptions.ResourceNotFoundException;
import freevoice.features.models.VideoEntity;
import freevoice.features.repositories.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class VideoServiceImpl implements VideoService{

    @Autowired
    private VideoRepository videoRepository;

    @Override
    public VideoEntity createPost(VideoEntity videos) {
        if(videos.getTitle().isEmpty()) {
            throw new ResourceNotFoundException("402" ,"please field required details");
        }
        try {
            VideoEntity saveVideo = videoRepository.save(videos);
            videos.setAddedDate(new Date());
            videos.setVideoName("default.mp4");
            return videoRepository.save(saveVideo);
        }catch(IllegalArgumentException i) {
            throw new ResourceNotFoundException("401" ,"hey your data is Empty");
        }catch(Exception e) {
            throw new ResourceNotFoundException("401" ,"something is wrong"+e.getMessage());
        }
    }

    @Override
    public VideoEntity getById(Long id) {
        return this.videoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("504", "id is not present"));
    }

    @Override
    public List<VideoEntity> getAll() {
        List<VideoEntity> listOfVideo;
        try {
            listOfVideo = this.videoRepository.findAll();
            return listOfVideo ;
        } catch(Exception e) {
            throw new ResourceNotFoundException("404","i am sorry "+e.getMessage());
        }
    }

    @Override
    public VideoEntity updatePost(VideoEntity videos, Long id) {
        VideoEntity video = this.videoRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("501","Id not found"));
        video.setTitle(videos.getTitle());
        video.setDescription(videos.getDescription());
        video.setTags(videos.getTags());
        video.setAddedDate(new Date());
        return this.videoRepository.save(video);
    }

    @Override
    public void deleteVideos(Long id) {
        VideoEntity video = this.videoRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("403","video id not found"));
        this.videoRepository.delete(video);

    }
}
