package org.steamclone.services.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.steamclone.dto.PaymentMethodDTO;
import org.steamclone.model.entities.PaymentMethod;
import org.steamclone.repositories.PaymentMethodRepo;
import org.steamclone.repositories.UserRepo;
import org.steamclone.services.interfaces.PaymentMethodInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PaymentMethodInterfaceImpl implements PaymentMethodInterface {

    @Autowired
    private PaymentMethodRepo paymentMethodRepo;

    @Autowired
    private UserRepo userRepo;

    @Override
    public int createPaymentMethod(PaymentMethodDTO paymentMethodDTO) throws Exception {

        Optional<PaymentMethod> foundPaymentMethod = paymentMethodRepo.findByCardNumber(paymentMethodDTO.getCardNumber());

        if(foundPaymentMethod.isPresent()){
            throw new Exception("Ya existe un metodo de pago con el numero " + paymentMethodDTO.getCardNumber());
        }

        PaymentMethod paymentMethod = new PaymentMethod();

        paymentMethod.setBankingEntity(paymentMethodDTO.getBankingEntity());
        paymentMethod.setCvv(paymentMethodDTO.getCvv());
        paymentMethod.setCardNumber(paymentMethodDTO .getCardNumber());
        paymentMethod.setExpirationDate(paymentMethodDTO.getExpirationDate());
        paymentMethod.setTitularName(paymentMethodDTO.getTitularName());
        paymentMethod.setUser(userRepo.findById(paymentMethodDTO.getIdUser()).get());
        paymentMethod.setState(true);

        paymentMethodRepo.save(paymentMethod);

        return paymentMethod.getId();
    }

    @Override
    public int updatePaymentMethod(int idPaymentMethod, PaymentMethodDTO paymentMethodDTO) throws Exception {

        Optional<PaymentMethod> foundPaymentMethod = paymentMethodRepo.findById(idPaymentMethod);

        if (foundPaymentMethod.isEmpty()) {
            throw new Exception("El metodo de pago con id " + idPaymentMethod + " no existe");
        }

        PaymentMethod updatePaymentMethod = foundPaymentMethod.get();

        updatePaymentMethod.setBankingEntity(paymentMethodDTO.getBankingEntity());
        updatePaymentMethod.setCvv(paymentMethodDTO.getCvv());
        updatePaymentMethod.setCardNumber(paymentMethodDTO .getCardNumber());
        updatePaymentMethod.setExpirationDate(paymentMethodDTO.getExpirationDate());
        updatePaymentMethod.setTitularName(paymentMethodDTO.getTitularName());
        updatePaymentMethod.setUser(userRepo.findById(paymentMethodDTO.getIdUser()).get());

        paymentMethodRepo.save(updatePaymentMethod);

        return updatePaymentMethod.getId();
    }

    @Override
    public boolean deleteAPaymentMethod(int idPaymentMethod) throws Exception {

        Optional<PaymentMethod> foundPaymentMethod = paymentMethodRepo.findById(idPaymentMethod);

        if (foundPaymentMethod.isEmpty()) {
            throw new Exception("El metodo de pago con id " + idPaymentMethod + " no existe");
        }

        PaymentMethod deletePaymentMethod = foundPaymentMethod.get();

        deletePaymentMethod.setState(false);

        paymentMethodRepo.save(deletePaymentMethod);

        return deletePaymentMethod.isState();
    }

    @Override
    public List<PaymentMethodDTO> listPaymentMethodByIdUser(int idUser) {

        List<PaymentMethod> listPaymentMethods = paymentMethodRepo.findByUser(idUser);
        List<PaymentMethodDTO> listPaymentMethodDTO = new ArrayList<>();

        for (PaymentMethod paymentMethod : listPaymentMethods) {
            listPaymentMethodDTO.add(convertToGetDTO(paymentMethod));
        }

        return listPaymentMethodDTO;
    }

    @Override
    public PaymentMethodDTO getPaymentMethodDTO(int idPaymentMethod) {
        return convertToGetDTO(paymentMethodRepo.findById(idPaymentMethod).get());
    }

    @Override
    public PaymentMethod getPaymentMethod(int idPaymentMethod) throws Exception {

        Optional<PaymentMethod> foundPaymentMethod = paymentMethodRepo.findById(idPaymentMethod);

        if (foundPaymentMethod.isEmpty()) {
            throw new Exception("No existe un metodo de pago con el id " + idPaymentMethod);
        }

        PaymentMethod paymentMethod = foundPaymentMethod.get();
        return paymentMethod;
    }

    private PaymentMethodDTO convertToGetDTO(PaymentMethod paymentMethod) {
        PaymentMethodDTO paymentMethodDTO = new PaymentMethodDTO(
                paymentMethod.getId(),
                paymentMethod.getBankingEntity(),
                paymentMethod.getTitularName(),
                paymentMethod.getCardNumber(),
                paymentMethod.getExpirationDate(),
                paymentMethod.getCvv(),
                paymentMethod.isState(),
                paymentMethod.getUser().getId()
        );

        return paymentMethodDTO;
    }
}
