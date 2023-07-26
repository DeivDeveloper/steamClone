package org.steamclone.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.steamclone.model.entities.PuntuationReview;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Setter
public class ReviewDTO {
    private int id;
    private PuntuationReview puntuation;
    private String comment;
    private LocalDate dateComment;
    private boolean state;
    private int idGame;
    private int idUser;
}
