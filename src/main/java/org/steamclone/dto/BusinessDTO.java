package org.steamclone.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.steamclone.model.entities.BusinessType;

@AllArgsConstructor
@Getter
@Setter
public class BusinessDTO {
    private int id;
    private String name;
    private boolean state;
    private BusinessType businessType;
}
