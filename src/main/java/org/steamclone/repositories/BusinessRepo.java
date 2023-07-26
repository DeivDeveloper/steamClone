package org.steamclone.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.steamclone.model.entities.Business;

import java.util.List;

@Repository
public interface BusinessRepo extends JpaRepository<Business, Integer> {
    @Query("select b from Business b where b.name like concat('%', :name, '%')")
    List<Business> findByName(String name);
    @Query("select b from Business b where b.name = :name")
    Business getBusinessByName(String name);
    @Query("select b from Business b where b.businessType = :businessType")
    List<Business> findByBusinessType(String businessType);
    @Query("select b from Business b join  b.games g where g.id = :idGame")
    List<Business> listBusinessByIdGame(int idGame);
}
