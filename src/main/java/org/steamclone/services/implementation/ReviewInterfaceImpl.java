package org.steamclone.services.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.steamclone.dto.ReviewDTO;
import org.steamclone.model.entities.PuntuationReview;
import org.steamclone.model.entities.Review;
import org.steamclone.repositories.GameRepo;
import org.steamclone.repositories.ReviewRepo;
import org.steamclone.repositories.UserRepo;
import org.steamclone.services.interfaces.ReviewInterface;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReviewInterfaceImpl implements ReviewInterface {

    @Autowired
    private ReviewRepo reviewRepo;

    @Autowired
    private GameRepo gameRepo;

    @Autowired
    private UserRepo userRepo;

    @Override
    public int createReview(ReviewDTO reviewDTO) {

        Review review = new Review();

        review.setPuntuation(reviewDTO.getPuntuation());
        review.setComment(reviewDTO.getComment());
        review.setDateComment(reviewDTO.getDateComment());
        review.setState(true);
        review.setGame(gameRepo.findById(reviewDTO.getIdGame()).get());
        review.setUser(userRepo.findById(reviewDTO.getIdUser()).get());

        reviewRepo.save(review);

        return review.getId();
    }

    @Override
    public int updateReview(int idReview, ReviewDTO reviewDTO) throws Exception {

        Optional<Review> foundReview = reviewRepo.findById(idReview);

        if (foundReview.isEmpty()) {
            throw new Exception("La review con id " + idReview + " no existe");
        }

        Review updaReview = foundReview.get();

        updaReview.setPuntuation(reviewDTO.getPuntuation());
        updaReview.setComment(reviewDTO.getComment());
        updaReview.setDateComment(reviewDTO.getDateComment());
        updaReview.setGame(gameRepo.findById(reviewDTO.getIdGame()).get());
        updaReview.setUser(userRepo.findById(reviewDTO.getIdUser()).get());

        reviewRepo.save(updaReview);

        return updaReview.getId();
    }

    @Override
    public boolean deleteReview(int idReview) throws Exception {

        Optional<Review> foundReview = reviewRepo.findById(idReview);

        if (foundReview.isEmpty()) {
            throw new Exception("La review con id " + idReview + " no existe");
        }

        Review deleteReview = foundReview.get();

        deleteReview.setState(false);

        reviewRepo.save(deleteReview);

        return deleteReview.isState();
    }

    @Override
    public List<ReviewDTO> listReviewByIdGame(int idGame) {

        List<Review> listReviews = reviewRepo.findByIdGame(idGame);
        List<ReviewDTO> listReviewDTO = new ArrayList<>();

        for (Review review : listReviews) {
            listReviewDTO.add(convertToGetDTO(review));
        }

        return listReviewDTO;
    }

    @Override
    public List<ReviewDTO> listReviewByIdUser(int idUser) {

        List<Review> listReviews = reviewRepo.findByIdUser(idUser);
        List<ReviewDTO> listReviewDTO = new ArrayList<>();

        for (Review review : listReviews) {
            listReviewDTO.add(convertToGetDTO(review));
        }

        return listReviewDTO;
    }

    @Override
    public List<ReviewDTO> listReviewByPuntuation(PuntuationReview puntuation) {

        List<Review> listReviews = reviewRepo.findByPuntuation(puntuation);
        List<ReviewDTO> listReviewDTO = new ArrayList<>();

        for (Review review : listReviews) {
            listReviewDTO.add(convertToGetDTO(review));
        }

        return listReviewDTO;
    }

    @Override
    public List<ReviewDTO> listReviewByDateComment(LocalDate dateComment) {

        List<Review> listReviews = reviewRepo.findByDateComment(dateComment);
        List<ReviewDTO> listReviewDTO = new ArrayList<>();

        for (Review review : listReviews) {
            listReviewDTO.add(convertToGetDTO(review));
        }

        return listReviewDTO;
    }

    @Override
    public ReviewDTO getReviewDTO(int idReview) {
        return convertToGetDTO(reviewRepo.findById(idReview).get());
    }

    private ReviewDTO convertToGetDTO(Review review) {
        ReviewDTO reviewDTO = new ReviewDTO(
                review.getId(),
                review.getPuntuation(),
                review.getComment(),
                review.getDateComment(),
                review.isState(),
                review.getGame().getId(),
                review.getUser().getId()
        );

        return reviewDTO;
    }
}
