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
    private PuntuationReview puntuation;
    @Column(nullable = false)
    private String comment;
    @Column(nullable = false)
    private LocalDate dateComment;
    @Column(nullable = false)
    private boolean state;

    @ManyToOne
    private Game game;
    @ManyToOne
    private User user;
}
