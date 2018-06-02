package pl.edu.wat.pze.kwejk.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Picture {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String path;

    @NotEmpty(message = "Tytuł nie może być pusty")
    @Size(max = 50)
    private String title;

    @Size(max = 150)
    private String description;

    private int points;

    @DateTimeFormat
    private Date date;

    @Column(columnDefinition = "text")
//    @Size(min = 100, max = 9000)
    private String article;

}
