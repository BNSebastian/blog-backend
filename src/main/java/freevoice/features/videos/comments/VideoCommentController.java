package freevoice.features.videos.comments;

import freevoice.features.videos.comments.models.VideoCommentUpdateDto;
import freevoice.features.videos.comments.models.VideoCommentDto;
import freevoice.shared.constants.URLS;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(URLS.videoComment)
@AllArgsConstructor
public class VideoCommentController {

    @Autowired
    private VideoCommentService videoCommentService;

    @PostMapping(URLS.createVideoComment)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<VideoCommentDto> createComment(@RequestBody VideoCommentDto comment) {
        return new ResponseEntity<>(videoCommentService.createComment(comment), HttpStatus.OK);
    }

    @GetMapping(URLS.getVideoComment)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<VideoCommentDto> getComment(@PathVariable("commentId") Long commentId) {
        return new ResponseEntity<>(videoCommentService.getComment(commentId), HttpStatus.OK);
    }

    @GetMapping(URLS.getAllVideoComments)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<VideoCommentDto>> getAllComments(@PathVariable("videoName") String videoName) {
        return new ResponseEntity<>(videoCommentService.getAllComments(videoName), HttpStatus.OK);
    }

    @PatchMapping(URLS.updateVideoComment)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<VideoCommentDto> updateComment(@RequestBody VideoCommentUpdateDto comment) {
        return new ResponseEntity<>(videoCommentService.updateComment(comment), HttpStatus.OK);
    }

    @DeleteMapping(URLS.deleteVideoComment)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> deleteComment(@PathVariable("commentId") Long commentId) {
        videoCommentService.deleteComment(commentId);
        String message = "Successfully deleted the comment with id: " + commentId;
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

}
