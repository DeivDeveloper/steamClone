package org.steamclone.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.steamclone.models.entities.Transaction;

@Repository
public interface TransactionRepo extends JpaRepository<Transaction, Integer> {
}
