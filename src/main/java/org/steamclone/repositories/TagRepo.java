package org.steamclone.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.steamclone.model.entities.Tag;

import java.util.List;

@Repository
public interface TagRepo {
    @Query("select t from Tag t join t.games g where g.id= :idGame")
    List<Tag> listTagByIdGame(int idGame);
    @Query("select t from Tag t where t.id= :idTag")
    Tag getTag(int idTag);
}
