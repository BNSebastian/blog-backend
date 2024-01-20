package freevoice.features.forum.posts.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ForumPostCreateDto {
    private String name;
    private String userEmail;
    private String initialCommentContent;
}
