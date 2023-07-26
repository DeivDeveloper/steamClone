package org.steamclone.services.interfaces;

import org.steamclone.dto.TransactionDTO;

import java.util.List;
public interface TransactionInterface {
    public int createTransaction(TransactionDTO transactionDTO);
    public List<TransactionDTO> listTransactionByIdUser(int idUser);
    public TransactionDTO getTransactionDTO (int idTransaction);
}
