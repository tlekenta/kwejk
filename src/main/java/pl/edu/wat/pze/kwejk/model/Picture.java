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
import java.util.List;

@Entity
@Table(name = "PICTURE")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Picture {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PICTURE_ID")
    private Long id;

    private String path;

    @NotEmpty(message = "Tytuł nie może być pusty")
    @Size(max = 30, message = "Tytuł może miec maksymalnie 30 znaków")
    private String title;

    @Size(max = 150, message = "Opis może mieć maksymalnie 150 znaków")
    private String description;

    private int points;

    @DateTimeFormat
    private Date date;

    @ManyToOne
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;

    @OneToMany
    private List<Comment> comments;

    @Column(columnDefinition = "text")
    @Size(max = 500000)
    private String article;

    @Override
    public String toString() {
        return "Picture{" +
                "id=" + id +
                ", path='" + path + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", points=" + points +
                ", date=" + date +
                ", user=" + user +
                ", article='" + article + '\'' +
                '}';
    }
}
