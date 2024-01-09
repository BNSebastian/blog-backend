package freevoice.features.videos.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import static jakarta.persistence.CascadeType.ALL;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "video")
public class Video{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    @Column(length = 4000)
    private String description;

    @Lob
    private byte[] data;

    @OneToMany(cascade=ALL, mappedBy = "video")
    private List<VideoComment> videoComments;

    public Video(String name, byte[] data) {
        this.name = name;
        this.data = data;
    }
}