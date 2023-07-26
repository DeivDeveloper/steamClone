package org.steamclone.model.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class User implements Serializable {
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String nickname;
    @Column(nullable = false, length = 16)
    private String cellphoneNumber;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private LocalDate birthday;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private int level;
    @Column(nullable = false)
    private String country;
    @Column(nullable = false)
    private Rol rol;
    @Column(nullable = false)
    private boolean state;

    @OneToMany(mappedBy = "user")
    private List<Transaction> transactions;
    @OneToMany(mappedBy = "user")
    private List<ProfileComment> writeProfileComments;
    @OneToMany(mappedBy = "user")
    private List<ProfileComment> profileUserComments;
    @OneToMany(mappedBy = "user")
    private List<PaymentMethod> paymentMethods;
    @OneToMany(mappedBy = "user")
    private List<Review> reviews;

    @ManyToMany
    private List<Achievement> achievements;
    @ManyToMany
    private List<Game> wishGames;
    @ManyToMany(mappedBy = "user")
    private List<Game> games;
}
