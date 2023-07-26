package org.steamclone.services.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.steamclone.dto.TransactionDTO;
import org.steamclone.dto.TransactionDetailDTO;
import org.steamclone.model.entities.Transaction;
import org.steamclone.model.entities.TransactionDetail;
import org.steamclone.repositories.PaymentMethodRepo;
import org.steamclone.repositories.TransactionRepo;
import org.steamclone.repositories.UserRepo;
import org.steamclone.services.interfaces.TransactionDetailInterface;
import org.steamclone.services.interfaces.TransactionInterface;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionInterfaceImpl implements TransactionInterface {

    @Autowired
    private TransactionDetailInterface transactionDetailInterface;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PaymentMethodRepo paymentMethodRepo;

    @Autowired
    private TransactionRepo transactionRepo;

    @Override
    public int createTransaction(TransactionDTO transactionDTO) {

        Transaction transaction = new Transaction();

        transaction.setTotalPrice(transactionDTO.getTotalPrice());
        transaction.setDate(transactionDTO.getDate());

        List<TransactionDetail> listTransactionDetails = new ArrayList<>();
        for (TransactionDetailDTO transactionDetailDTO: transactionDTO.getTransactionDetails()) {
            listTransactionDetails.add(transactionDetailInterface.getTransactionDetail(transactionDetailDTO.getId()));
        }
        transaction.setTransactionDetails(listTransactionDetails);

        transaction.setUser(userRepo.findById(transactionDTO.getIdUser()).get());
        transaction.setPaymentMethod(paymentMethodRepo.findById(transactionDTO.getIdPaymentMethod()).get());

        transactionRepo.save(transaction);

        return transaction.getId();
    }

    @Override
    public List<TransactionDTO> listTransactionByIdUser(int idUser) {

        List<Transaction> listTransactions = transactionRepo.listTransactionByUser(idUser);
        List<TransactionDTO> listTransactionDTO = new ArrayList<>();

        for (Transaction transaction : listTransactions) {
            listTransactionDTO.add(convertToGetDTO(transaction));
        }

        return listTransactionDTO;
    }

    @Override
    public TransactionDTO getTransactionDTO(int idTransaction) {
        return convertToGetDTO(transactionRepo.findById(idTransaction).get());
    }

    private TransactionDTO convertToGetDTO(Transaction transaction) {
        TransactionDTO transactionDTO = new TransactionDTO(
                transaction.getId(),
                transaction.getTotalPrice(),
                transaction.getDate(),
                transactionDetailInterface.listTransactionDetailByIdTransaction(transaction.getId()),
                transaction.getUser().getId(),
                transaction.getPaymentMethod().getId()
        );

        return transactionDTO;
    }
}
