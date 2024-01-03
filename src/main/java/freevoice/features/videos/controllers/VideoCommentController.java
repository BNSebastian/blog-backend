package freevoice.features.videos.controllers;

import freevoice.features.videos.models.VideoCommentUpdateDto;
import freevoice.features.videos.models.VideoCommentDto;
import freevoice.features.videos.persistence.VideoCommentService;
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
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<VideoCommentDto> createComment(@RequestBody VideoCommentDto comment) {
        return new ResponseEntity<>(videoCommentService.createComment(comment), HttpStatus.OK);
    }

    @GetMapping("/getComment/{commentId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<VideoCommentDto> getComment(@PathVariable Long commentId) {
        return new ResponseEntity<>(videoCommentService.getComment(commentId), HttpStatus.OK);
    }

    @GetMapping("/getAllComments/{videoName}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<VideoCommentDto>> getAllComments(@PathVariable String videoName) {
        return new ResponseEntity<>(videoCommentService.getAllComments(videoName), HttpStatus.OK);
    }

    @PatchMapping("/updateComment")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<VideoCommentDto> updateComment(@RequestBody VideoCommentUpdateDto comment) {
        return new ResponseEntity<>(videoCommentService.updateComment(comment), HttpStatus.OK);
    }

    @DeleteMapping("/deleteComment/{commentId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> deleteComment(@PathVariable Long commentId) {
        videoCommentService.deleteComment(commentId);
        String message = "Successfully deleted the comment with id: " + commentId;
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

}
