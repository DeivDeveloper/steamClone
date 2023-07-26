package org.steamclone.services.interfaces;

import org.steamclone.dto.ProfileCommentDTO;

import java.util.List;

public interface ProfileCommentInterface {
    public int createProfileComment (ProfileCommentDTO profileCommentDTO);
    public boolean deleteProfileComment (int idProfileComment) throws Exception;
    public List<ProfileCommentDTO> listProfileCommentByIdUser (int idUser);
    public ProfileCommentDTO getProfileCommentDTO (int idProfileComment);
}
