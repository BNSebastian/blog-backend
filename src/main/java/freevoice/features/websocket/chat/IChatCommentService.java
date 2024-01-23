package freevoice.features.websocket.chat;

import freevoice.features.websocket.chat.models.ChatCommentDto;
import freevoice.features.websocket.chat.models.ChatCommentCreateDto;

import java.util.List;

public interface IChatCommentService {
    ChatCommentDto createComment(ChatCommentCreateDto chatCommentDto);
    ChatCommentDto getComment(Long commentId);
    List<ChatCommentDto> getAllComments();
}
