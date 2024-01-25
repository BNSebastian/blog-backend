package freevoice.features.blog.articles;

import freevoice.features.blog.articles.models.ArticleEntity;
import freevoice.shared.templates.DefaultService;
import org.springframework.beans.factory.annotation.Autowired;

public class ArticleService extends DefaultService<ArticleEntity> implements IArticleService {

    @Autowired
    private IArticleRepository articleRepository;

    @Override
    public ArticleEntity getByName(String name) {
        return articleRepository
                .findByName(name)
                .orElseThrow();
    }
}
