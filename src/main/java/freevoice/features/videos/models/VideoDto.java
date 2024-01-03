package freevoice.features.videos.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // lombok
@AllArgsConstructor // lombok
@NoArgsConstructor // lombok
@Builder // needed for tests
public class VideoDto {
    private String name;
    private byte[] data;

    public static VideoDto mapToDto(Video video) {
        return VideoDto.builder()
                       .name(video.getName())
                       .data(video.getData())
                       .build();
    }
}
