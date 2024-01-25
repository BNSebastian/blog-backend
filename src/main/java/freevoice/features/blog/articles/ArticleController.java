package freevoice.features.blog.articles;

import freevoice.features.blog.articles.models.ArticleEntity;
import freevoice.shared.constants.URLS;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(URLS.articles)
@AllArgsConstructor
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @PostMapping(URLS.createArticle)
    public ResponseEntity<ArticleEntity> create (@RequestBody ArticleEntity article) {
        return new ResponseEntity<>(articleService.create(article), HttpStatus.OK);
    }

    @GetMapping(URLS.getAllArticles)
    public ResponseEntity<List<ArticleEntity>> getAll() {
        return new ResponseEntity<>(articleService.getAll(), HttpStatus.OK);
    }

    @GetMapping(URLS.getArticleByName)
    public ResponseEntity<ArticleEntity> getByName(@PathVariable("name") String name) {
        return new ResponseEntity<>(articleService.getByName(name), HttpStatus.OK);
    }

    @DeleteMapping(URLS.deleteArticle)
    public ResponseEntity<String> delete(@RequestBody ArticleEntity articleEntity) {
        articleService.delete(articleEntity);
        return new ResponseEntity<>("Entry deleted", HttpStatus.OK);
    }
}
