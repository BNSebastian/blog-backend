package freevoice.features.controllers;

import freevoice.features.models.dtos.UpdateVideoCommentDto;
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

    @PostMapping("/createComment")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<VideoCommentDto> createComment(@RequestBody VideoCommentDto comment) {
        return new ResponseEntity<>(videoCommentService.createComment(comment), HttpStatus.CREATED);
    }

    @GetMapping("/getComment/{commentId}")
    @ResponseStatus(HttpStatus.FOUND)
    public ResponseEntity<VideoCommentDto> getComment(@PathVariable Long commentId) {
        return new ResponseEntity<>(videoCommentService.getComment(commentId), HttpStatus.FOUND);
    }

    @GetMapping("/getAllComments/{videoName}")
    @ResponseStatus(HttpStatus.FOUND)
    public ResponseEntity<List<VideoCommentDto>> getAllComments(@PathVariable String videoName) {
        return new ResponseEntity<>(videoCommentService.getAllComments(videoName), HttpStatus.FOUND);
    }

    @PatchMapping("/updateComment")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<VideoCommentDto> updateComment(@RequestBody UpdateVideoCommentDto comment) {
        return new ResponseEntity<>(videoCommentService.updateComment(comment), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/deleteComment/{commentId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<String> deleteComment(@PathVariable Long commentId) {
        videoCommentService.deleteComment(commentId);
        String message = "Successfully deleted the comment with id: " + commentId;
        return new ResponseEntity<>(message, HttpStatus.ACCEPTED);
    }

}
