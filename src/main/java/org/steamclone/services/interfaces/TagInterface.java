package org.steamclone.services.interfaces;

import org.steamclone.dto.TagDTO;
import org.steamclone.model.entities.Tag;

import java.util.List;

public interface TagInterface {
    public List<TagDTO> listTagByIdGame(int idGame);
    public Tag getTag(int id);
}
