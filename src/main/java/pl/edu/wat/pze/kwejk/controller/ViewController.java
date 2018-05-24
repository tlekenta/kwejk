package pl.edu.wat.pze.kwejk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.edu.wat.pze.kwejk.model.ModelAttributeEnum;
import pl.edu.wat.pze.kwejk.model.ViewEnum;
import pl.edu.wat.pze.kwejk.service.ArticleService;

@Controller
@RequestMapping("/article")
public class ArticleController {

    private ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/{aArticleId}")
    public String getArticle(@PathVariable Long aArticleId, Model aModel) {

        aModel.addAttribute(ModelAttributeEnum.ACTIVE_VIEW.toString(), ViewEnum.VIEW);
        aModel.addAttribute(ModelAttributeEnum.ACTUAL_ARTICLE.toString(), articleService.getArticle(aArticleId));

        return "index";
    }

}
