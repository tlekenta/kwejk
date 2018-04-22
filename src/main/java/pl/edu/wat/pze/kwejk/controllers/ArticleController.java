package pl.edu.wat.pze.kwejk.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.edu.wat.pze.kwejk.model.ViewEnum;
import pl.edu.wat.pze.kwejk.services.ArticleService;

@Controller
@RequestMapping("/article")
public class ArticleController {

    private ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/{aArticleId}")
    public String getArticle(@PathVariable Long aArticleId, Model aModel) {

        aModel.addAttribute("activeView", ViewEnum.ARTICLE);
        aModel.addAttribute("actualArticle", articleService.getArticle(aArticleId));

        return "index";
    }

}
