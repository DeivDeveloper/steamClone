package org.steamclone.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.steamclone.model.entities.Achievement;

import java.util.List;

@Repository
public interface AchievementRepo extends JpaRepository<Achievement, Integer> {
    @Query("select a from Achievement a where a.game.id = :idGame")
    List<Achievement> listAchievementByIdGame(int idGame);
    @Query("select a from Achievement a join a.users u where u.id = :idUser")
    List<Achievement> listAchievementByIdUser(int idUser);
}
