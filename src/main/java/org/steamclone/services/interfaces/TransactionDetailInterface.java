package org.steamclone.services.interfaces;

import org.steamclone.dto.TransactionDetailDTO;
import org.steamclone.model.entities.TransactionDetail;

import java.util.List;

public interface TransactionDetailInterface {
    public int createTransactionDetail(TransactionDetailDTO TransactionDetailDTO);
    public List<TransactionDetailDTO> listTransactionDetailByIdTransaction(int idTransaction);
    public TransactionDetailDTO getTransactionDetailDTO (int idTransactionDetail);
    public TransactionDetail getTransactionDetail(int idTransactionDetail);
}
