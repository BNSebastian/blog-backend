package freevoice.features.blog.articles;

import freevoice.features.blog.articles.models.ArticleEntity;
import freevoice.shared.templates.GenericRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends GenericRepository<ArticleEntity, Long> {
}
