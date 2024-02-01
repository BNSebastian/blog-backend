package freevoice.features.blog.articles;
import freevoice.features.blog.articles.models.ArticleEntity;
import freevoice.shared.templates.GenericService;
import org.springframework.stereotype.Service;

@Service
public class ArticleService extends GenericService<ArticleEntity, Long> {

    private final ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        super(articleRepository);
        this.articleRepository = articleRepository;
    }
}
