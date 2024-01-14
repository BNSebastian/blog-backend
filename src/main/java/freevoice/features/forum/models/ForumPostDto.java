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
public class ForumPostDto {
    private Long id;
    private String name;
    private String userEmail;
    private String createdOn;
    private Long viewerCount;
    private boolean pinned;

    public static ForumPostDto mapToDto(ForumPost forumPost) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm dd-MM-yy");

        long views = 0L;
        if (forumPost.getViewerList() != null) {
            views = forumPost.getViewerList().size();
        }

        return ForumPostDto.builder()
                           .id(forumPost.getId())
                           .name(forumPost.getName())
                           .userEmail(forumPost.getUserEntity().getEmail())
                           .viewerCount(views)
                           .pinned(forumPost.isPinned())
                           .createdOn(forumPost.getCreatedOn().format(formatter))
                           .build();
    }
}
