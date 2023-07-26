package org.steamclone.services.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.steamclone.dto.BusinessDTO;
import org.steamclone.dto.GameDTO;
import org.steamclone.dto.ImageDTO;
import org.steamclone.dto.TagDTO;
import org.steamclone.model.entities.Business;
import org.steamclone.model.entities.Game;
import org.steamclone.model.entities.Tag;
import org.steamclone.repositories.GameRepo;
import org.steamclone.services.interfaces.BusinessInterface;
import org.steamclone.services.interfaces.GameInterface;
import org.steamclone.services.interfaces.TagInterface;

import java.util.*;

@Service
public class GameInterfaceImpl implements GameInterface {

    @Autowired
    private BusinessInterface businessInterface;

    @Autowired
    private TagInterface tagInterface;

    @Autowired
    private GameRepo gameRepo;

    @Override
    public int createGame(GameDTO gameDTO) {

        Game game = new Game();

        game.setName(gameDTO.getName());
        game.setReleaseDate(gameDTO.getReleaseDate());
        game.setRealPrice(gameDTO.getRealPrice());
        float realPrice = gameDTO.getRealPrice();
        int discount = gameDTO.getDiscount();
        game.setPrice(calculatePrice(realPrice, discount));
        game.setDiscount(gameDTO.getDiscount());
        game.setDescription(gameDTO.getDescription());
        game.setRequirements(gameDTO.getRequirements());
        game.setClassification(gameDTO.getClassification());
        game.setPuntuation(0);
        game.setState(true);
        game.setImages(convert(gameDTO.getImages()));

        List<Tag> listTags = new ArrayList<>();
        for (TagDTO tagDTO: gameDTO.getTags()) {
            listTags.add(tagInterface.getTag(tagDTO.getId()));
        }
        game.setTags(listTags);

        List<Business> listBusiness = new ArrayList<>();
        for (BusinessDTO businessDTO: gameDTO.getBusiness()) {
            listBusiness.add(businessInterface.getBusiness(businessDTO.getId()));
        }
        game.setBusiness(listBusiness);

        game.setLanguages(gameDTO.getLanguages());

        gameRepo.save(game);

        return game.getId();
    }

    @Override
    public int updateGame(int idGame, GameDTO gameDTO) throws Exception {

        Optional<Game> foundGame = gameRepo.findById(idGame);

        if (foundGame.isEmpty()) {
            throw new Exception("El juego con id " + idGame + " no existe");
        }

        Game updateGame = foundGame.get();

        updateGame.setName(gameDTO.getName());
        updateGame.setReleaseDate(gameDTO.getReleaseDate());
        updateGame.setRealPrice(gameDTO.getRealPrice());
        float realPrice = gameDTO.getRealPrice();
        int discount = gameDTO.getDiscount();
        updateGame.setPrice(calculatePrice(realPrice, discount));
        updateGame.setDiscount(gameDTO.getDiscount());
        updateGame.setDescription(gameDTO.getDescription());
        updateGame.setRequirements(gameDTO.getRequirements());
        updateGame.setClassification(gameDTO.getClassification());
        updateGame.setPuntuation(gameDTO.getPuntuation());
        updateGame.setImages(convert(gameDTO.getImages()));

        List<Tag> listTags = new ArrayList<>();
        for (TagDTO tagDTO: gameDTO.getTags()) {
            listTags.add(tagInterface.getTag(tagDTO.getId()));
        }
        updateGame.setTags(listTags);

        List<Business> listBusiness = new ArrayList<>();
        for (BusinessDTO businessDTO: gameDTO.getBusiness()) {
            listBusiness.add(businessInterface.getBusiness(businessDTO.getId()));
        }
        updateGame.setBusiness(listBusiness);

        updateGame.setLanguages(gameDTO.getLanguages());

        gameRepo.save(updateGame);

        return updateGame.getId();
    }

    @Override
    public boolean deleteGame(int idGame) throws Exception {

        Optional<Game> foundGame = gameRepo.findById(idGame);

        if (foundGame.isEmpty()) {
            throw new Exception("El juego con id " + idGame + " no existe");
        }

        Game deleteGame = foundGame.get();

        deleteGame.setState(false);

        gameRepo.save(deleteGame);

        return deleteGame.isState();
    }

    @Override
    public List<GameDTO> listAllGame() {

        List<Game> listGames = gameRepo.findAll();
        List<GameDTO> listGameDTO = new ArrayList<>();

        for (Game game : listGames) {
            listGameDTO.add(convertToGetDTO(game));
        }

        return listGameDTO;
    }

    @Override
    public List<GameDTO> listGameByName(String name) {

        List<Game> listGames = gameRepo.listGameByName(name);
        List<GameDTO> listGameDTO = new ArrayList<>();

        for (Game game : listGames) {
            listGameDTO.add(convertToGetDTO(game));
        }

        return listGameDTO;
    }

    @Override
    public List<GameDTO> listGameByTag(String tag) {

        List<Game> listGames = gameRepo.listGameByTag(tag);
        List<GameDTO> listGameDTO = new ArrayList<>();

        for (Game game : listGames) {
            listGameDTO.add(convertToGetDTO(game));
        }

        return listGameDTO;
    }

    @Override
    public List<GameDTO> listGameByPrice(float minPrice, float maxPrice) {

        List<Game> listGames = gameRepo.listGameByPrice(minPrice, maxPrice);
        List<GameDTO> listGameDTO = new ArrayList<>();

        for (Game game : listGames) {
            listGameDTO.add(convertToGetDTO(game));
        }

        return listGameDTO;
    }

    @Override
    public List<GameDTO> listGameByIdBusiness(int idBusiness) {

        List<Game> listGames = gameRepo.listGameByIdBusiness(idBusiness);
        List<GameDTO> listGameDTO = new ArrayList<>();

        for (Game game : listGames) {
            listGameDTO.add(convertToGetDTO(game));
        }

        return listGameDTO;
    }

    @Override
    public List<GameDTO> listFavoriteGame(int idUser) {

        List<Game> listGames = gameRepo.listFavoriteGame(idUser);
        List<GameDTO> listGameDTO = new ArrayList<>();

        for (Game game : listGames) {
            listGameDTO.add(convertToGetDTO(game));
        }

        return listGameDTO;
    }

    @Override
    public List<GameDTO> listGameByIdUser(int idUser) {

        List<Game> listGames = gameRepo.listGameByIdUser(idUser);
        List<GameDTO> listGameDTO = new ArrayList<>();

        for (Game game : listGames) {
            listGameDTO.add(convertToGetDTO(game));
        }

        return listGameDTO;
    }

    @Override
    public GameDTO getGameDTO(int idGame) {
        return convertToGetDTO(gameRepo.findById(idGame).get());
    }

    private GameDTO convertToGetDTO(Game game) {
        GameDTO gameDTO = new GameDTO(
                game.getId(),
                game.getName(),
                game.getReleaseDate(),
                game.getRealPrice(),
                game.getPrice(),
                game.getDiscount(),
                game.getDescription(),
                game.getRequirements(),
                game.getClassification(),
                game.getPuntuation(),
                game.isState(),
                convert(game.getImages()),
                tagInterface.listTagByIdGame(game.getId()),
                businessInterface.listByIdGame(game.getId()),
                game.getLanguages()
        );

        return gameDTO;
    }

    private Map<String, String> convert(List<ImageDTO> imageDTOS) {
        Map<String, String> mapa = new HashMap<>();
        imageDTOS.forEach(obj -> mapa.put(obj.getId(), obj.getUrl()));
        return mapa;
    }

    private List<ImageDTO> convert(Map<String, String> map) {

        List<ImageDTO> lista = new ArrayList<>();

        for (String key : map.keySet()) {
            lista.add(new ImageDTO(key, map.get(key)));
        }

        return lista;
    }

    private float calculatePrice(float realPrice, int discount) {
        System.out.println(realPrice);
        System.out.println(discount);
        float mountDiscount = (realPrice*discount)/100;
        System.out.println(mountDiscount);
        float price = realPrice-mountDiscount;
        System.out.println(price);
        return price;
    }
}
