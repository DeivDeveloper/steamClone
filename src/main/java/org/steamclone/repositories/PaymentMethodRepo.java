package org.steamclone.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.steamclone.model.entities.PaymentMethod;

import java.util.List;
import java.util.Optional;

@Repository
public interface PaymentMethodRepo extends JpaRepository<PaymentMethod, Integer> {
    @Query("select p from PaymentMethod p where p.cardNumber = :cardNumber")
    Optional<PaymentMethod> findByCardNumber(String cardNumber);
    @Query("select p from PaymentMethod p where p.user.id = :idUser and p.state = true")
    List<PaymentMethod> findByUser(String idUser);
}
