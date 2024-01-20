package freevoice.features.chat;

import freevoice.features.chat.models.ChatCommentDto;
import freevoice.features.chat.models.ChatCommentCreateDto;

import java.util.List;

public interface ChatCommentService {
    ChatCommentDto createComment(ChatCommentCreateDto chatCommentDto);
    ChatCommentDto getComment(Long commentId);
    List<ChatCommentDto> getAllComments();
}
