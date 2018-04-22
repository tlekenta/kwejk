package pl.edu.wat.pze.kwejk.services;

import org.springframework.stereotype.Service;
import pl.edu.wat.pze.kwejk.MockService;
import pl.edu.wat.pze.kwejk.model.Article;

@Service
public class ArticleService {

    private MockService mockService;

    public ArticleService(MockService mockService) {
        this.mockService = mockService;
    }

    public Article getArticle(int aArticleId) {
        return mockService.getMOCK_ARTICLES()[aArticleId - 1];
    }

}
