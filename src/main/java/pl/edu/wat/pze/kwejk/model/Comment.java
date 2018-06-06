package pl.edu.wat.pze.kwejk.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name="USER_ID")
    private User user;

    @Column(columnDefinition = "text")
    private String commentText;

    private LocalDateTime postDate;

}
