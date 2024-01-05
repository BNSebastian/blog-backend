package freevoice.features.forum.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.format.DateTimeFormatter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ForumCommentDto {
    private Long id;
    private Long postId;
    private String content;
    private String userEmail;
    private String createdOn;

    public static ForumCommentDto mapToDto(ForumComment forumPost) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm dd-MM-yy");
        return ForumCommentDto.builder()
                              .id(forumPost.getId())
                              .postId(forumPost.getPost().getId())
                              .content(forumPost.getContent())
                              .userEmail(forumPost.getUserEntity().getEmail())
                              .createdOn(forumPost.getCreatedOn().format(formatter))
                              .build();
    }
}
