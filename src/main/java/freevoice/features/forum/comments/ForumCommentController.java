package freevoice.features.forum.comments;

import freevoice.features.forum.comments.models.ForumCommentCreateDto;
import freevoice.features.forum.comments.models.ForumCommentDto;
import freevoice.shared.constants.URLS;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(URLS.forumComment)
@AllArgsConstructor
public class ForumCommentController {

    @Autowired
    private ForumCommentService commentService;

    @PostMapping(URLS.createForumComment)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ForumCommentDto> create(@RequestBody ForumCommentCreateDto comment) {
        return new ResponseEntity<>(commentService.create(comment), HttpStatus.OK);
    }

    @GetMapping(URLS.getAllForumComments)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<ForumCommentDto>> getAll(@PathVariable Long id) {
        System.out.println("pathvariable " + id);
        return new ResponseEntity<>(commentService.getAllByPostId(id), HttpStatus.OK);
    }

    @DeleteMapping(URLS.deleteForumComment)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> delete(@RequestBody ForumCommentDto comment) throws Exception {
        commentService.delete(comment);
        String message = "Successfully deleted the comment with id: " + comment.getId();
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @PostMapping(URLS.likeForumComment)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Long> like(@PathVariable Long id, @RequestBody String userEmail) {
        return new ResponseEntity<>(commentService.likeComment(id, userEmail), HttpStatus.OK);
    }

    @PostMapping(URLS.dislikeForumComment)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Long> dislike(@PathVariable Long id, @RequestBody String userEmail) {
        return new ResponseEntity<>(commentService.dislikeComment(id, userEmail), HttpStatus.OK);
    }
}
