package org.steamclone.services.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.steamclone.dto.TransactionDetailDTO;
import org.steamclone.model.entities.TransactionDetail;
import org.steamclone.repositories.GameRepo;
import org.steamclone.repositories.TransactionDetailRepo;
import org.steamclone.repositories.TransactionRepo;
import org.steamclone.services.interfaces.TransactionDetailInterface;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionDetailInterfaceImpl implements TransactionDetailInterface {

    @Autowired
    private TransactionDetailRepo transactionDetailRepo;

    @Autowired
    private TransactionRepo transactionRepo;

    @Autowired
    private GameRepo gameRepo;

    @Override
    public int createTransactionDetail(TransactionDetailDTO transactionDetailDTO) {

        TransactionDetail transactionDetail = new TransactionDetail();

        transactionDetail.setPrice(transactionDetailDTO.getPrice());
        transactionDetail.setTransaction(transactionRepo.findById(transactionDetailDTO.getIdTransaction()).get());
        transactionDetail.setGame(gameRepo.findById(transactionDetailDTO.getIdGame()).get());

        transactionDetailRepo.save(transactionDetail);

        return transactionDetail.getId();
    }

    @Override
    public List<TransactionDetailDTO> listTransactionDetailByIdTransaction(int idTransaction) {
        List<TransactionDetail> listTransactionDetails = transactionDetailRepo.findByTransaction(idTransaction);
        List<TransactionDetailDTO> listTransactionDetailDTO = new ArrayList<>();
        for (TransactionDetail transactionDetail : listTransactionDetails) {
            listTransactionDetailDTO.add(convertToGetDTO(transactionDetail));
        }
        return listTransactionDetailDTO;
    }

    @Override
    public TransactionDetailDTO getTransactionDetailDTO(int idTransactionDetail) {
        return convertToGetDTO(transactionDetailRepo.findById(idTransactionDetail).get());
    }

    @Override
    public TransactionDetail getTransactionDetail(int idTransactionDetail) {
        return transactionDetailRepo.getTransactionDetail(idTransactionDetail);
    }

    private TransactionDetailDTO convertToGetDTO(TransactionDetail transactionDetail) {
        TransactionDetailDTO transactionDetailDTO = new TransactionDetailDTO(
                transactionDetail.getId(),
                transactionDetail.getPrice(),
                transactionDetail.getTransaction().getId(),
                transactionDetail.getGame().getId()
        );

        return transactionDetailDTO;
    }
}
