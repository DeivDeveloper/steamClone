package org.steamclone.services.interfaces;

import org.steamclone.dto.TransactionDTO;
import org.steamclone.dto.UserDTO;
import org.steamclone.model.entities.User;

import java.util.List;

public interface UserInterface {
    public int createUser(UserDTO userDTO) throws Exception;
    public int updateUser(int idUser, UserDTO userDTO) throws Exception;
    public boolean deleteUser(int idUser) throws Exception;
    public UserDTO findByNickName(String nickName);
    public UserDTO getUserDTO (int idUser);
}
