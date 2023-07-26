package org.steamclone.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Setter
public class ProfileCommentDTO {
    private int id;
    private String comment;
    private LocalDate dateComment;
    private boolean state;
    private int idUser;
    private int idProfileUser;
}
