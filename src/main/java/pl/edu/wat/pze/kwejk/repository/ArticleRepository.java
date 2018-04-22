package pl.edu.wat.pze.kwejk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.wat.pze.kwejk.model.Article;

public interface ArticleRepository extends JpaRepository<Article, Long> {
}
