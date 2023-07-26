package org.steamclone.services.interfaces;

import org.steamclone.dto.GameDTO;

import java.util.List;

public interface GameInterface {
    public int createGame(GameDTO gameDTO);
    public int updateGame(int idGame, GameDTO gameDTO) throws Exception;
    public boolean deleteGame(int idGame) throws Exception;
    public List<GameDTO> listAllGame();
    public List<GameDTO> listGameByName(String name);
    public List<GameDTO> listGameByTag(String tag);
    public List<GameDTO> listGameByPrice(float minPrice, float maxPrice);
    public List<GameDTO> listGameByIdBusiness(int idBusiness);
    public List<GameDTO> listFavoriteGame(int idUser);
    public List<GameDTO> listGameByIdUser(int idUser);
    public GameDTO getGameDTO (int idGame);
}
