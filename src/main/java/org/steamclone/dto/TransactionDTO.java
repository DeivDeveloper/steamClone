package org.steamclone.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class TransactionDTO {
    private int id;
    private float totalPrice;
    private LocalDate date;
    private List<TransactionDetailDTO> transactionDetails;
    private int idUser;
    private int idPaymentMethod;
}
