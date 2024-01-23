package freevoice.features.articles;

import freevoice.features.articles.models.Article;
import freevoice.shared.templates.DefaultService;
import org.springframework.beans.factory.annotation.Autowired;

public class ArticleService extends DefaultService<Article> implements IArticleService {

    @Autowired
    private IArticleRepository articleRepository;

    @Override
    public Article getByName(String name) {
        return articleRepository
                .findByName(name)
                .orElseThrow();
    }
}
