package org.steamclone.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.steamclone.model.entities.Review;

import java.util.List;

@Repository
public interface ReviewRepo extends JpaRepository<Review, Integer> {
    @Query("select r from Review r where r.user.nickname = :nickname")
    List<Review> findByNickName(String nickname);
    @Query("select r from Review r where r.dateComment = :dateComment")
    List<Review> findByDateComment(String dateComment);
    @Query("select r from Review r where r.game.name = :gameName")
    List<Review> findByGameName(String gameName);
}
