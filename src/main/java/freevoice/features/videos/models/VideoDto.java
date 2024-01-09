package freevoice.features.videos.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data // lombok
@AllArgsConstructor // lombok
@NoArgsConstructor // lombok
@Builder // needed for tests
public class VideoDto {
    private String name;
    private String description;

    public static VideoDto mapToDto(Video video) {
        return VideoDto.builder()
                       .name(video.getName())
                       .description(video.getDescription())
                       .build();
    }
}
