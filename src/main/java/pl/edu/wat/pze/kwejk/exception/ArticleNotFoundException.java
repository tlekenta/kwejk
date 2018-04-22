package pl.edu.wat.pze.kwejk.exception;

import java.text.MessageFormat;

public class ArticleNotFoundException extends RuntimeException {

    public ArticleNotFoundException(Long aArticleId) {
        super(MessageFormat.format("Article with id = {0} does not exists", aArticleId));
    }
}
