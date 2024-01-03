package freevoice.features.videos.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.format.DateTimeFormatter;

@Data // lombok
@AllArgsConstructor // lombok
@NoArgsConstructor // lombok
@Builder // needed for tests
public class VideoCommentDto {
    private Long id;
    private Long parentId;
    private String videoName;
    private String userEmail;
    private String content;
    private String createdOn;

    public static VideoCommentDto mapToDto(VideoComment videoComment) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm dd-MM-yy");
        return VideoCommentDto.builder()
                .id(videoComment.getId())
                .parentId(videoComment.getParentId())
                .videoName(videoComment.getVideo().getName())
                .userEmail(videoComment.getUser().getEmail())
                .content(videoComment.getContent())
                .createdOn(videoComment.getCreatedOn().format(formatter))
                .build();
    }
}
