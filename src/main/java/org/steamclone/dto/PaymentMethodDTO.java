package org.steamclone.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Setter
public class PaymentMethodDTO {
    private int id;
    private String bankingEntity;
    private String titularName;
    private String cardNumber;
    private LocalDate expirationDate;
    private int cvv;
    private boolean state;
    private int idUser;
}
