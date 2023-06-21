package org.steamclone.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.steamclone.models.entities.Business;

@Repository
public interface BusinessRepo extends JpaRepository<Business, Integer> {
}
