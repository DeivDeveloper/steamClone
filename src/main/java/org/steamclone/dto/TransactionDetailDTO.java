package org.steamclone.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class TransactionDetailDTO {
    private int id;
    private float price;
    private int idTransaction;
    private int idGame;
}
