package freevoice.features.blog.articles.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "article")
public class ArticleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(length = 40000)
    private String content;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp // not sure if it will work
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm MM/dd/yyyy")
    private LocalDateTime createdOn;
}
