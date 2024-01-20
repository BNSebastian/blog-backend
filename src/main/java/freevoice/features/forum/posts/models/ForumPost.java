package freevoice.features.forum.posts.models;

import freevoice.core.user.UserEntity;
import freevoice.features.forum.comments.models.ForumComment;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

import static jakarta.persistence.CascadeType.ALL;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "forum_posts")
public class ForumPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500)
    private String name;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    private LocalDateTime createdOn;

    @OneToMany(cascade=ALL, mappedBy = "post")
    public List<ForumComment> comments;

    private List<String> viewerList;

    private boolean isPinned;
}
