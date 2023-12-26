package freevoice.features.controllers;

import freevoice.features.models.dtos.VideoCommentDto;
import freevoice.features.services.VideoCommentService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/videoComment")
@AllArgsConstructor
public class VideoCommentController {

    @Autowired
    private VideoCommentService videoCommentService;

    @GetMapping("/getAllByVideoName")
    public ResponseEntity<List<VideoCommentDto>> getAllByVideoName(@RequestBody String videoName) {
        return new ResponseEntity<>(videoCommentService.getAllByVideoName(videoName), HttpStatus.OK);
    }

    @PostMapping("/createComment")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<VideoCommentDto> createComment(@RequestBody VideoCommentDto comment) {
        return new ResponseEntity<>(videoCommentService.create(comment), HttpStatus.CREATED);
    }
}
