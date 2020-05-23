package com.example.PayMyBuddy.dao;

import com.example.PayMyBuddy.modeles.Amis;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;

public interface AmisRepository extends JpaRepository<Amis, Integer> {

    Amis getAmisByAmisAmisAndAmisUser(int idamis, int iduser);

    ArrayList<Amis> getAmisByAmisUser(int iduser);

}
