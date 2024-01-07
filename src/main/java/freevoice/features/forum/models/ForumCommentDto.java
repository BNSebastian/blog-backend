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
    private Long likes;
    private Long dislikes;

    public static ForumCommentDto mapToDto(ForumComment forumPost) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm dd-MM-yy");

        long likes = 0L;
        if (forumPost.getLikes() != null) {
            likes = forumPost.getLikes().size();
        }

        long dislikes = 0L;
        if (forumPost.getDislikes() != null) {
            dislikes = forumPost.getDislikes().size();
        }

        return ForumCommentDto.builder()
                              .id(forumPost.getId())
                              .postId(forumPost.getPost().getId())
                              .content(forumPost.getContent())
                              .userEmail(forumPost.getUserEntity().getEmail())
                              .createdOn(forumPost.getCreatedOn().format(formatter))
                              .likes(likes)
                              .dislikes(dislikes)
                              .build();
    }
}
