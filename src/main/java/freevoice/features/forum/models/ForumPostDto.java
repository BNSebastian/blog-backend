package freevoice.features.forum.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ForumPostDto {
    private Long id;
    private String name;
    private String userEmail;
    private String createdOn;

    public static ForumPostDto mapToDto(ForumPost forumPost) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm dd-MM-yy");
        return ForumPostDto.builder()
                           .id(forumPost.getId())
                           .name(forumPost.getName())
                           .userEmail(forumPost.getUserEntity().getEmail())
                           .createdOn(forumPost.getCreatedOn().format(formatter))
                           .build();
    }
}
