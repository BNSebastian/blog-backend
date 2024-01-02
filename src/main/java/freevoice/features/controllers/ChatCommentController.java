package freevoice.features.controllers;

import freevoice.features.models.dtos.ChatCommentDto;
import freevoice.features.models.dtos.CreateChatCommentDto;
import freevoice.features.services.ChatCommentService;
import freevoice.shared.URLS;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(URLS.chat)
@AllArgsConstructor
public class ChatCommentController {

    @Autowired
    private ChatCommentService chatCommentService;

    @PostMapping(URLS.createChatComment)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ChatCommentDto> createComment(@RequestBody CreateChatCommentDto request) {
        return new ResponseEntity<>(chatCommentService.createComment(request), HttpStatus.OK);
    }

    @GetMapping(URLS.getChatCommentById)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ChatCommentDto> getComment(@PathVariable Long commentId) {
        return new ResponseEntity<>(chatCommentService.getComment(commentId), HttpStatus.OK);
    }

    @GetMapping(URLS.getAllChatComments)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<ChatCommentDto>> getAllComments() {
        return new ResponseEntity<>(chatCommentService.getAllComments(), HttpStatus.OK);
    }
}
