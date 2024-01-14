package freevoice.features.videos.controllers;

import freevoice.features.videos.models.ChangeVideoNameRequest;
import freevoice.features.videos.models.Video;
import freevoice.features.videos.models.VideoDto;
import freevoice.features.videos.services.VideoService;
import freevoice.shared.constants.URLS;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    @PostMapping(URLS.uploadVideos)
    public ResponseEntity<List<String>> uploadVideos(@RequestParam("files") List<MultipartFile> multipartFiles) throws IOException {
        List<String> uploadedFiles = new ArrayList<>();
        for(MultipartFile file : multipartFiles) {
            String filename = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
            Video savedVideo = videoService.uploadVideo(file, filename);
            uploadedFiles.add(savedVideo.getName());
        }
        return new ResponseEntity<>(uploadedFiles, HttpStatus.OK);
    }

    @PostMapping(URLS.changeVideo)
    public ResponseEntity<VideoDto> changeVideo(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        String filename = StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        Video savedVideo = videoService.changeVideo(multipartFile, filename);
        return new ResponseEntity<>(VideoDto.mapToDto(savedVideo), HttpStatus.OK);
    }

    @PostMapping(URLS.changeVideoName)
    public ResponseEntity<String> changeVideoName(@RequestBody ChangeVideoNameRequest request) {
        return new ResponseEntity<>(videoService.changeVideoName(request.getOldName(), request.getNewName()), HttpStatus.OK);
    }

    @GetMapping(URLS.playVideo)
    public ResponseEntity<ByteArrayResource> playVideo(@PathVariable("name") String name){
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(new ByteArrayResource(videoService.getVideo(name).getData()));
    }

    @GetMapping(URLS.getAllVideoNames)
    public ResponseEntity<List<String>> getAllVideoNames(){
        return ResponseEntity.ok(videoService.getAllVideoNames());
    }

    @GetMapping(URLS.getAllVideos)
    public ResponseEntity<List<VideoDto>> getAllVideos(){
        return new ResponseEntity<>(videoService.getAllVideos(), HttpStatus.OK);
    }

    @GetMapping(URLS.getVideoByName)
    public ResponseEntity<VideoDto> getVideoByName(@PathVariable("name") String videoName){
        return new ResponseEntity<>(videoService.getVideoDto(videoName), HttpStatus.OK);
    }

    @PostMapping(URLS.setVideoDescription)
    public ResponseEntity<String> setDescription(
            @PathVariable("name") String name,
            @RequestBody String description
    ){
        return new ResponseEntity<>(videoService.setVideoDescription(name, description), HttpStatus.OK);
    }

    @GetMapping(URLS.getVideoDescription)
    public ResponseEntity<String> getDescription(@PathVariable("name") String name){
        return new ResponseEntity<>(videoService.getVideoDescription(name), HttpStatus.OK);
    }

    @DeleteMapping(URLS.deleteVideo)
    public ResponseEntity<Boolean> deleteVideo(@PathVariable("name") String name){
        return new ResponseEntity<>(videoService.deleteVideo(name), HttpStatus.OK);
    }
}