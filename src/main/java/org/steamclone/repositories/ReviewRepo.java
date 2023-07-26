package org.steamclone.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.steamclone.model.entities.PuntuationReview;
import org.steamclone.model.entities.Review;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReviewRepo extends JpaRepository<Review, Integer> {
    @Query("select r from Review r where r.game.id = :idGame")
    List<Review> findByIdGame(int idGame);
    @Query("select r from Review r where r.user.id = :idUser")
    List<Review> findByIdUser(int idUser);
    @Query("select r from Review r where r.puntuation = :puntuation")
    List<Review> findByPuntuation(PuntuationReview puntuation);
    @Query("select r from Review r where r.dateComment = :dateComment")
    List<Review> findByDateComment(LocalDate dateComment);
}
