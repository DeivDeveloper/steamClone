package org.steamclone.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.steamclone.models.entities.Achievement;

@Repository
public interface AchievementRepo extends JpaRepository<Achievement, Integer> {
}
