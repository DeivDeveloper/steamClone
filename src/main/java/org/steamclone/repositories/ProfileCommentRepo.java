package org.steamclone.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.steamclone.models.entities.ProfileComment;

@Repository
public interface ProfileCommentRepo extends JpaRepository<ProfileComment, Integer> {
}
