package pl.edu.wat.pze.kwejk.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Article {

    private Long id;

    private String title;

    //zmapować na odpowiedni typ
    private String content;

    //not null
    private Picture picture;

}
