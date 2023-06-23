package org.steamclone.model.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Review implements Serializable {
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private int puntuation;
    @Column(nullable = false)
    private String comment;
    @Column(nullable = false)
    private LocalDate dateComment;

    @ManyToOne
    private Game game;
    @ManyToOne
    private User user;
}
