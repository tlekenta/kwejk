package pl.edu.wat.pze.kwejk.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
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
//    @Max(value = 50, message = "Tytuł może mieć maksymalnie 50 znaków")
    private String title;

//    @Max(value = 150, message = "Opis może mieć maksymalnie 150 znaków")
    private String description;

    private int points;

    @DateTimeFormat
    private Date date;

    @Column(columnDefinition = "text")
//    @Min(value = 100, message = "Artykuł musi miec conajmniej 100 znaków")
    private String article;

}
