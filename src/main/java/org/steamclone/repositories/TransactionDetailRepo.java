package org.steamclone.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.steamclone.models.entities.TransactionDetail;

@Repository
public interface TransactionDetailRepo extends JpaRepository<TransactionDetail, Integer> {
}
