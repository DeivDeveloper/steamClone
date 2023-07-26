package org.steamclone.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.steamclone.model.entities.User;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
    @Query("select u from User u where u.nickname like concat('%', :nickname, '%')")
    User findByNickName(String nickName);
    @Query("select u from User u where u.email = :email")
    User findByEmail(String email);
}
