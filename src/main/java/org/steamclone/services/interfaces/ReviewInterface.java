package org.steamclone.services.interfaces;

import org.steamclone.dto.ReviewDTO;
import org.steamclone.model.entities.PuntuationReview;

import java.time.LocalDate;
import java.util.List;

public interface ReviewInterface {
    public int createReview(ReviewDTO reviewDTO);
    public int updateReview(int idReview, ReviewDTO reviewDTO) throws Exception;
    public boolean deleteReview(int idReview) throws Exception;
    public List<ReviewDTO> listReviewByIdGame(int idGame);
    public List<ReviewDTO> listReviewByIdUser(int idUser);
    public List<ReviewDTO> listReviewByPuntuation(PuntuationReview puntuation);
    public List<ReviewDTO> listReviewByDateComment(LocalDate dateComment);
    public ReviewDTO getReviewDTO (int idReview);
}
