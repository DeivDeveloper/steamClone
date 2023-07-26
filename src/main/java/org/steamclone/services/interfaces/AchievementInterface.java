package org.steamclone.services.interfaces;

import org.steamclone.dto.AchievementDTO;

import java.util.List;

public interface AchievementInterface {
    public int createAchievement(AchievementDTO achievementDTO) throws Exception;
    public int updateAchievement(int idAchievement, AchievementDTO achievementDTO) throws Exception;
    public boolean deleteAchievement(int idAchievement) throws Exception;
    public List<AchievementDTO> addAchievementUser(int idAchievement, int idUser) throws Exception;
    public List<AchievementDTO> listAchievementByIdGame(int idGame);
    public List<AchievementDTO> listAchievementByIdUser(int idUser);
    public AchievementDTO getAchievementDTO (int idAchievement);
}
