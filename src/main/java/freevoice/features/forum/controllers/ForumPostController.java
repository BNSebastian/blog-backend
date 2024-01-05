package freevoice.features.forum.controllers;

import freevoice.features.forum.models.ForumCommentCreateDto;
import freevoice.features.forum.models.ForumPostCreateDto;
import freevoice.features.forum.models.ForumPostDto;
import freevoice.features.forum.services.ForumPostService;
import freevoice.shared.URLS;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(URLS.forumPost)
@AllArgsConstructor
public class ForumPostController {

    @Autowired
    private ForumPostService postService;

    @PostMapping(URLS.createForumPost)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ForumPostDto> createPost(
            @RequestBody ForumPostCreateDto post,
            @RequestBody ForumCommentCreateDto comment
    ) {
        return new ResponseEntity<>(postService.create(post, comment), HttpStatus.OK);
    }

    @GetMapping(URLS.getAllForumPosts)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<ForumPostDto>> getAllPosts() {
        return new ResponseEntity<>(postService.getAll(), HttpStatus.OK);
    }

    @DeleteMapping(URLS.deleteForumPost)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> deleteComment(
            @PathVariable Long postId,
            @RequestBody String userEmail
    ) throws Exception {
        postService.deletePost(postId, userEmail);
        String message = "Successfully deleted the comment with id: " + postId;
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
