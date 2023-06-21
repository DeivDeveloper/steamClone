package org.steamclone.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.steamclone.models.entities.PaymentMethod;

@Repository
public interface PaymentMethodRepo extends JpaRepository<PaymentMethod, Integer> {
}
