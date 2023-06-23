package org.steamclone.model.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Game implements Serializable {
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private LocalDate releaseDate;
    @Column(nullable = false)
    private double realPrice;
    @Column(nullable = false)
    private double price;
    @Column(nullable = false, length = 3)
    private float discount;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private String requirements;
    @Column(nullable = false)
    private String classification;
    @Column(nullable = false)
    private String puntuation;

    @ElementCollection
    private List<String> languages;
    @ElementCollection(fetch = FetchType.EAGER)
    private Map<String,String> images;

    @OneToMany(mappedBy = "game")
    private List<Review> reviews;
    @OneToMany(mappedBy = "game")
    private List<Tag> tags;
    @OneToMany(mappedBy = "game")
    private List<Business> developer;
    @OneToMany(mappedBy = "game")
    private List<Achievement> achievements;
    @OneToMany(mappedBy = "game")
    private List<TransactionDetail> transactionDetails;

    @ManyToOne
    private User user;
    @ManyToOne
    private Business editor;

    @ManyToMany(mappedBy = "wishGames")
    private List<User> wishGameUsers;
}