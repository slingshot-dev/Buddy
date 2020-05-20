package com.example.PayMyBuddy.dao;

import com.example.PayMyBuddy.modeles.Amis;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AmisRepository extends JpaRepository<Amis, Integer> {

    Amis getAmisByAmisAmisAndAmisUser(int idamis, int iduser);

    Amis getByAmisUser(String email);

}
