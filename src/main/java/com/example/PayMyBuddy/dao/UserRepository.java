package com.example.PayMyBuddy.dao;

import com.example.PayMyBuddy.modeles.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User getUSERByEmail(String email);
    User getUserByIduser(int idUser);

}
