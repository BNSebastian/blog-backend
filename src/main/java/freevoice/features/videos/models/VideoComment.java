package freevoice.features.videos.models;

import freevoice.core.user.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "comments")
public class VideoComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long parentId;

    @ManyToOne
    @JoinColumn(name="video_id", nullable=false)
    private Video video;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @Column(length = 4000)
    private String content;

    private LocalDateTime createdOn;
}
