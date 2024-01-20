package freevoice.features.forum.comments.models;

import freevoice.core.user.UserEntity;
import freevoice.features.forum.posts.models.ForumPost;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "forum_comments")
public class ForumComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "forum_posts_id")
    private ForumPost post;

    @Column(length = 4000)
    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    private LocalDateTime createdOn;

    private List<String> likes;

    private List<String> dislikes;


}
