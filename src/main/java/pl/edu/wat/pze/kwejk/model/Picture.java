package pl.edu.wat.pze.kwejk.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Picture {

    private Long id;

    private String path;

    private String title;

    private String description;

    //nullable
    private Article article;

}
