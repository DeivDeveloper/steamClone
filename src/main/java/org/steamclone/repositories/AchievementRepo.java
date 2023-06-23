package org.steamclone.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.steamclone.model.entities.Achievement;
import org.steamclone.model.entities.PaymentMethod;

import java.util.List;

@Repository
public interface AchievementRepo extends JpaRepository<Achievement, Integer> {
    @Query("select a from Achievement a where a.name = :name")
    Achievement findByName(String name);
    @Query("select a from Achievement a where a.game.name = :gameName")
    List<Achievement> findByGameName(String gameName);
}
