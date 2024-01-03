package freevoice.features.chat.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.format.DateTimeFormatter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatCommentDto {
    private Long id;
    private String userEmail;
    private String content;
    private String createdOn;

    public static ChatCommentDto mapToDto(ChatComment chatComment) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm dd-MM-yy");
        return ChatCommentDto.builder()
                .id(chatComment.getId())
                .userEmail(chatComment.getUser().getEmail())
                .content(chatComment.getContent())
                .createdOn(chatComment.getCreatedOn().format(formatter))
                .build();
    }
}
