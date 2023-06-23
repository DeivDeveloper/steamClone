package org.steamclone.model.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class PaymentMethod implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 50,nullable = false)
    private String bankingEntity;
    @Column(length = 16,nullable = false)
    private String cardNumber;
    @Column(length = 100, nullable = false)
    private String titularName;
    @Column(nullable = false)
    private LocalDate expirationDate;
    @Column(nullable = false,length = 3)
    private int cvv;
    @Column(nullable = false,length = 3)
    private  boolean state;

    @OneToMany(mappedBy = "paymentMethod")
    private List<Transaction> transactions;

    @ManyToOne
    private User user;
}
