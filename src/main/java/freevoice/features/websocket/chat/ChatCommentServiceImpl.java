package freevoice.features.websocket.chat;

import freevoice.core.user.UserEntity;
import freevoice.core.user.UserRepository;
import freevoice.features.websocket.chat.models.ChatComment;
import freevoice.features.websocket.chat.models.ChatCommentDto;
import freevoice.features.websocket.chat.models.ChatCommentCreateDto;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ChatCommentServiceImpl implements ChatCommentService {

    @Autowired
    private ChatCommentRepository chatCommentRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public ChatCommentDto createComment(ChatCommentCreateDto request) {
        UserEntity user = userRepository
                .findByEmail(request.getUserEmail())
                .orElseThrow();

        ChatComment newComment = ChatComment
                .builder()
                .content(request.getContent())
                .user(user)
                .createdOn(LocalDateTime.now())
                .build();

        ChatComment savedComment = chatCommentRepository.save(newComment);

        return ChatCommentDto.mapToDto(savedComment);
    }

    @Override
    public ChatCommentDto getComment(Long commentId) {
        ChatComment chatComment = chatCommentRepository
                .findById(commentId)
                .orElseThrow();

        return ChatCommentDto.mapToDto(chatComment);
    }

    @Override
    public List<ChatCommentDto> getAllComments() {
        List<ChatComment> chatComments = chatCommentRepository.findAll();
        return chatComments
                .stream()
                .map(ChatCommentDto::mapToDto)
                .collect(Collectors.toList());
    }

}
