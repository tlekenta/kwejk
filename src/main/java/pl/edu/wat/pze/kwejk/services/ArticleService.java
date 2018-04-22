package pl.edu.wat.pze.kwejk.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.edu.wat.pze.kwejk.exception.ArticleNotFoundException;
import pl.edu.wat.pze.kwejk.model.Article;
import pl.edu.wat.pze.kwejk.repository.ArticleRepository;

@Service
@AllArgsConstructor
public class ArticleService {

    ArticleRepository articleRepository;

    public Article getArticle(Long aArticleId) {
        return articleRepository.findById(aArticleId).orElseThrow(() -> new ArticleNotFoundException(aArticleId));
    }

}
