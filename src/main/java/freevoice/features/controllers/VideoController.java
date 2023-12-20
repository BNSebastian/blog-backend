package freevoice.features.controllers;

import freevoice.features.exceptions.ControllerException;
import freevoice.features.exceptions.ResourceNotFoundException;
import freevoice.features.models.VideoEntity;
import freevoice.features.services.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/video")
public class VideoController {

    @Autowired
    private VideoService videoService;

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<VideoEntity> save(@RequestBody VideoEntity videoEntity) {
        return new ResponseEntity<>(videoService.createPost(videoEntity), HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        try {
            VideoEntity video = videoService.getById(id);
            return new ResponseEntity<VideoEntity>(video, HttpStatus.CREATED);
        } catch (ResourceNotFoundException e) {
            ControllerException controllerException = new ControllerException(e.getErrorCode(), e.getErrorMessage() + e.getMessage());
            return new ResponseEntity<ControllerException>(controllerException, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            ControllerException controllerException = new ControllerException("504", "id not found");
            return new ResponseEntity<ControllerException>(controllerException, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAll() {
        VideoEntity videos = new VideoEntity();
        try {
            return new ResponseEntity<List<VideoEntity>>(videoService.getAll(), HttpStatus.CREATED);
        } catch (Exception e) {
            ControllerException controllerException = new ControllerException("404", "Empty database is found");
            return new ResponseEntity<ControllerException>(controllerException, HttpStatus.BAD_REQUEST);
        }
    }
}
