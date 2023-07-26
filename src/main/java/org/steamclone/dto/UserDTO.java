package org.steamclone.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.steamclone.model.entities.Rol;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Setter
public class UserDTO {
    private int id;
    private String name;
    private String nickname;
    private String cellphoneNumber;
    private String email;
    private LocalDate birthday;
    private String password;
    private int level;
    private String country;
    private Rol rol;

    private boolean state;
}
