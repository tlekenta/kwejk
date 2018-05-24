package pl.edu.wat.pze.kwejk.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Picture {

    @Id
    private Long id;

    private String path;

    private String title;

    private String description;

    private int points;

    @Column(columnDefinition = "text")
    private String article;

}
