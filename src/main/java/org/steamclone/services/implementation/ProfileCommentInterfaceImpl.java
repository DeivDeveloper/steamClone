package org.steamclone.services.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.steamclone.dto.ProfileCommentDTO;
import org.steamclone.model.entities.ProfileComment;
import org.steamclone.repositories.ProfileCommentRepo;
import org.steamclone.repositories.UserRepo;
import org.steamclone.services.interfaces.ProfileCommentInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProfileCommentInterfaceImpl implements ProfileCommentInterface {

    @Autowired
    private ProfileCommentRepo profileCommentRepo;

    @Autowired
    private UserRepo userRepo;

    @Override
    public int createProfileComment(ProfileCommentDTO profileCommentDTO) {

        ProfileComment profileComment = new ProfileComment();

        profileComment.setComment(profileCommentDTO.getComment());
        profileComment.setDateComment(profileCommentDTO.getDateComment());
        profileComment.setState(true);
        profileComment.setUser(userRepo.findById(profileCommentDTO.getIdUser()).get());
        profileComment.setProfileUser(userRepo.findById(profileCommentDTO.getIdProfileUser()).get());

        profileCommentRepo.save(profileComment);

        return profileComment.getId();
    }

    @Override
    public boolean deleteProfileComment(int idProfileComment) throws Exception {

        Optional<ProfileComment> foundProfileComment = profileCommentRepo.findById(idProfileComment);

        if (foundProfileComment.isEmpty()) {
            throw new Exception("El comentario de perfil con id " + idProfileComment + " no existe");
        }

        ProfileComment deleProfileComment = foundProfileComment.get();

        deleProfileComment.setState(false);

        profileCommentRepo.save(deleProfileComment);

        return deleProfileComment.isState();
    }

    @Override
    public List<ProfileCommentDTO> listProfileCommentByIdUser(int idUser) {

        List<ProfileComment> listProfileComments = profileCommentRepo.listProfileCommentByIdUser(idUser);
        List<ProfileCommentDTO> listProfileCommentDTO = new ArrayList<>();

        for (ProfileComment profileComment : listProfileComments) {
            listProfileCommentDTO.add(convertToGetDTO(profileComment));
        }

        return listProfileCommentDTO;
    }

    @Override
    public ProfileCommentDTO getProfileCommentDTO(int idProfileComment) {
        return convertToGetDTO(profileCommentRepo.findById(idProfileComment).get());
    }

    private ProfileCommentDTO convertToGetDTO(ProfileComment profileComment) {
        ProfileCommentDTO profileCommentDTO = new ProfileCommentDTO(
                profileComment.getId(),
                profileComment.getComment(),
                profileComment.getDateComment(),
                profileComment.isState(),
                profileComment.getUser().getId(),
                profileComment.getProfileUser().getId()
        );

        return profileCommentDTO;
    }
}
