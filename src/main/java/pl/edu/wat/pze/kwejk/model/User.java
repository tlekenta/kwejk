package pl.edu.wat.pze.kwejk.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    private String password;
}
