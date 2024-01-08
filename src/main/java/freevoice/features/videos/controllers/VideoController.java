package freevoice.features.videos.controllers;

import freevoice.features.videos.models.Video;
import freevoice.features.videos.services.VideoService;
import freevoice.shared.URLS;
import lombok.AllArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(URLS.video)
@AllArgsConstructor
public class VideoController {
    private VideoService videoService;

//    @PostMapping()
//    public ResponseEntity<String> saveVideo(
//            @RequestParam("file") MultipartFile file,
//            @RequestParam("name") String name
//    ) throws IOException {
//        videoService.saveVideo(file, name);
//        return ResponseEntity.ok("Video saved successfully.");
//    }

    @PostMapping(URLS.uploadVideos)
    public ResponseEntity<List<String>> uploadFiles(@RequestParam("files") List<MultipartFile> multipartFiles) throws IOException {
        List<String> uploadedFiles = new ArrayList<>();
        for(MultipartFile file : multipartFiles) {
            String filename = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
            Video savedVideo = videoService.uploadVideo(file, filename);
            uploadedFiles.add(savedVideo.getName());
        }
        return new ResponseEntity<>(uploadedFiles, HttpStatus.OK);
    }


    @GetMapping(URLS.getVideoByName)
    public ResponseEntity<ByteArrayResource> getVideoByName(@PathVariable("name") String name){
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(new ByteArrayResource(videoService.getVideo(name).getData()));
    }

    @GetMapping(URLS.getAllVideoNames)
    public ResponseEntity<List<String>> getAllVideoNames(){
        return ResponseEntity.ok(videoService.getAllVideoNames());
    }
}