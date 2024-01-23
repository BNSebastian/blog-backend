package freevoice.features.articles;

import freevoice.shared.constants.URLS;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(URLS.articles)
@AllArgsConstructor
public class ArticleController {
}
