package freevoice.features.services.implementations;

import freevoice.features.exceptions.VideoAlreadyExistsException;
import freevoice.features.exceptions.VideoNotFoundException;
import freevoice.features.models.Video;
import freevoice.features.repositories.VideoRepository;
import freevoice.features.services.VideoService;
import org.springframework.transaction.annotation.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@AllArgsConstructor
public class VideoServiceImpl implements VideoService {

    private VideoRepository videoRepository;

    @Override
    @Transactional
    public void saveVideo(MultipartFile file, String name) throws IOException {
        if (videoRepository.existsByName(name)) {
            throw new VideoAlreadyExistsException();
        }
        Video newVid = new Video(name, file.getBytes());
        videoRepository.save(newVid);
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


}