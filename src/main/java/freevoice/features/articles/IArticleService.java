package freevoice.features.articles;

import freevoice.features.articles.models.Article;

public interface IArticleService {
    Article getByName(String name);
}
