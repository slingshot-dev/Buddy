package com.example.PayMyBuddy.dao;

import com.example.PayMyBuddy.modeles.Amis;
import com.example.PayMyBuddy.modeles.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AmisRepository extends JpaRepository<Amis, Integer> {

    Amis getAmisByAmisAmisAndAmisUser(int idamis, int iduser);



}
