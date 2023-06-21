package org.steamclone.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.steamclone.models.entities.Review;

@Repository
public interface ReviewRepo extends JpaRepository<Review, Integer> {
}
