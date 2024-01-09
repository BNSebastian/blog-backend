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
    public ResponseEntity<ForumPostDto> create(@RequestBody ForumPostCreateDto post) {
        return new ResponseEntity<>(postService.create(post), HttpStatus.OK);
    }

    @GetMapping(URLS.getAllForumPosts)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<ForumPostDto>> getAll() {
        return new ResponseEntity<>(postService.getAll(), HttpStatus.OK);
    }

    @GetMapping(URLS.getForumPostById)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ForumPostDto> getById(@PathVariable Long id) {
        return new ResponseEntity<>(postService.getById(id), HttpStatus.OK);
    }

    @DeleteMapping(URLS.deleteForumPost)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> delete(
            @PathVariable Long id,
            @RequestBody String userEmail
    ) throws Exception {
        postService.deletePost(id, userEmail);
        String message = "Successfully deleted the comment with id: " + id;
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}