package freevoice.features.models.dtos;

import freevoice.features.models.VideoComment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // lombok
@AllArgsConstructor // lombok
@NoArgsConstructor // lombok
@Builder // needed for tests
public class VideoCommentDto {
    private String videoName;
    private String userEmail;
    private String content;

    public static VideoCommentDto mapToDto(VideoComment videoComment) {
        return VideoCommentDto.builder()
                .videoName(videoComment.getVideo().getName())
                .userEmail(videoComment.getUser().getEmail())
                .content(videoComment.getContent())
                .build();
    }
}
