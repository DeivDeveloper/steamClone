package org.steamclone.services.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.steamclone.dto.AchievementDTO;
import org.steamclone.model.entities.Achievement;
import org.steamclone.model.entities.User;
import org.steamclone.repositories.AchievementRepo;
import org.steamclone.repositories.GameRepo;
import org.steamclone.repositories.UserRepo;
import org.steamclone.services.interfaces.AchievementInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AchievementInterfaceImpl implements AchievementInterface {

    @Autowired
    private AchievementRepo achievementRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private GameRepo gameRepo;

    @Override
    public int createAchievement(AchievementDTO achievementDTO) throws Exception {

        Achievement achievement = new Achievement();
        achievement.setName(achievementDTO.getName());
        achievement.setDescription(achievementDTO.getDescription());
        achievement.setState(true);
        achievement.setGame(gameRepo.findById(achievementDTO.getIdGame()).get());

        achievementRepo.save(achievement);

        return achievement.getId();
    }

    @Override
    public int updateAchievement(int idAchievement, AchievementDTO achievementDTO) throws Exception {

        Optional<Achievement> foundAchievement = achievementRepo.findById(idAchievement);

        if (foundAchievement.isEmpty()) {
            throw new Exception("El logro con id " + idAchievement + " no existe");
        }

        Achievement updateAchievement = foundAchievement.get();

        updateAchievement.setName(achievementDTO.getName());
        updateAchievement.setDescription(achievementDTO.getDescription());
        updateAchievement.setGame(gameRepo.findById(idAchievement).get());

        achievementRepo.save(updateAchievement);

        return updateAchievement.getId();
    }

    @Override
    public boolean deleteAchievement(int idAchievement) throws Exception {

        Optional<Achievement> foundAchievement = achievementRepo.findById(idAchievement);

        if (foundAchievement.isEmpty()) {
            throw new Exception("El logro con id " + idAchievement + " no existe");
        }

        Achievement deleteAchievement = foundAchievement.get();

        deleteAchievement.setState(false);

        achievementRepo.save(deleteAchievement);

        return deleteAchievement.isState();
    }

    @Override
    public List<AchievementDTO> addAchievementUser(int idAchievement, int idUser) throws Exception {

        Optional<Achievement> foundAchievement = achievementRepo.findById(idAchievement);
        Optional<User> foundUser = userRepo.findById(idUser);

        if (foundAchievement.isEmpty()) {
            throw new Exception("El logro con id " + idAchievement + " no existe");
        }
        if (foundUser.isEmpty()) {
            throw new Exception("El usuario con id " + idUser + " no existe");
        }

        Achievement achievement = foundAchievement.get();
        User user = foundUser.get();

        List<Achievement> listAchievements = user.getAchievements();
        listAchievements.add(achievement);
        user.setAchievements(listAchievements);
        List<Achievement> newListAchievements = user.getAchievements();
        List<AchievementDTO> listAchievementDTO = new ArrayList<>();
        for (Achievement achievementUser : newListAchievements){
            listAchievementDTO.add(convertToGetDTO(achievementUser));
        }
        return listAchievementDTO;
    }

    @Override
    public List<AchievementDTO> listAchievementByIdGame(int idGame) {

        List<Achievement> listAchievements = achievementRepo.listAchievementByIdGame(idGame);
        List<AchievementDTO> listAchievementDTO = new ArrayList<>();

        for (Achievement achievement : listAchievements) {
            listAchievementDTO.add(convertToGetDTO(achievement));
        }

        return listAchievementDTO;
    }

    @Override
    public List<AchievementDTO> listAchievementByIdUser(int idUser) {

        List<Achievement> listAchievements = achievementRepo.listAchievementByIdUser(idUser);
        List<AchievementDTO> listAchievementDTO = new ArrayList<>();

        for (Achievement achievement : listAchievements) {
            listAchievementDTO.add(convertToGetDTO(achievement));
        }

        return listAchievementDTO;
    }

    @Override
    public AchievementDTO getAchievementDTO(int idAchievement) {
        return convertToGetDTO(achievementRepo.findById(idAchievement).get());
    }

    private AchievementDTO convertToGetDTO(Achievement achievement) {
        AchievementDTO achievementDTO = new AchievementDTO(
                achievement.getId(),
                achievement.getName(),
                achievement.getDescription(),
                achievement.isState(),
                achievement.getGame().getId()
        );

        return achievementDTO;
    }
}
