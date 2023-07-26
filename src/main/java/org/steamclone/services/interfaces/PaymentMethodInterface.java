package org.steamclone.services.interfaces;

import org.steamclone.dto.PaymentMethodDTO;
import org.steamclone.model.entities.PaymentMethod;

import java.util.List;

public interface PaymentMethodInterface {
    public int createPaymentMethod(PaymentMethodDTO paymentMethodDTO) throws Exception;
    public int updatePaymentMethod(int idPaymentMethod, PaymentMethodDTO paymentMethodDTO) throws Exception;
    public boolean deleteAPaymentMethod(int idPaymentMethod) throws Exception;
    public List<PaymentMethodDTO> listPaymentMethodByIdUser(int idUser);
    public PaymentMethodDTO getPaymentMethodDTO (int idPaymentMethod);
    public PaymentMethod getPaymentMethod(int idPaymentMethod) throws Exception;
}
