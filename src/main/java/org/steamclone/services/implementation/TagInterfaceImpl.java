package org.steamclone.services.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.steamclone.dto.TagDTO;
import org.steamclone.model.entities.Tag;
import org.steamclone.repositories.TagRepo;
import org.steamclone.services.interfaces.GameInterface;
import org.steamclone.services.interfaces.TagInterface;

import java.util.ArrayList;
import java.util.List;

@Service
public class TagInterfaceImpl implements TagInterface {

    @Autowired
    private GameInterface gameInterface;

    @Autowired
    private TagRepo tagRepo;

    @Override
    public List<TagDTO> listTagByIdGame(int idGame) {
        List<Tag> listTags = tagRepo.listTagByIdGame(idGame);
        List<TagDTO> listTagDTO = new ArrayList<>();
        for (Tag tag : listTags) {
            listTagDTO.add(convertToGetDTO(tag));
        }
        return listTagDTO;
    }

    @Override
    public Tag getTag(int id) {
        return tagRepo.getTag(id);
    }

    private TagDTO convertToGetDTO(Tag tag) {
        TagDTO tagDTO = new TagDTO(
                tag.getId(),
                tag.getName(),
                gameInterface.listGameByTag(tag.getName())
        );

        return tagDTO;
    }
}
