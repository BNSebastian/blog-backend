package freevoice.features.forum.comments.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ForumCommentCreateDto {
    private Long postId;
    private String content;
    private String userEmail;
}
