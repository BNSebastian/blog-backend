package freevoice.features.services;

import freevoice.features.models.dtos.ChatCommentDto;
import freevoice.features.models.dtos.CreateChatCommentDto;

import java.util.List;

public interface ChatCommentService {
    ChatCommentDto createComment(CreateChatCommentDto chatCommentDto);
    ChatCommentDto getComment(Long commentId);
    List<ChatCommentDto> getAllComments();
}
