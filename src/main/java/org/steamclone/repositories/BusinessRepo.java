package org.steamclone.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.steamclone.model.entities.Achievement;
import org.steamclone.model.entities.Business;

import java.util.List;

@Repository
public interface BusinessRepo extends JpaRepository<Business, Integer> {
    @Query("select b from Business b where b.name = :name")
    Business findByName(String name);
    @Query("select b from Business b where b.game.name = :gameName")
    List<Business> findByGameName(String gameName);
}
