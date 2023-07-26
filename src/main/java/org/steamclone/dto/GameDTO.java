package org.steamclone.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class GameDTO {
    private int id;
    private String name;
    private LocalDate releaseDate;
    private float realPrice;
    private float price;
    private int discount;
    private String description;
    private String requirements;
    private String classification;
    private float puntuation;
    private boolean state;
    private List<ImageDTO> images;
    private List<TagDTO> tags;
    private List<BusinessDTO> business;
    private List<String> languages;
}
