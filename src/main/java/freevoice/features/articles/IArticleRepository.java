package freevoice.features.articles;

import freevoice.features.articles.models.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IArticleRepository extends JpaRepository<Article, Long> {
    Optional<Article> findByName(String name);
}
