package com.example.PayMyBuddy.dao;

import com.example.PayMyBuddy.modeles.MoyenPaiement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface MoyenPaiementRepository extends JpaRepository<MoyenPaiement, Integer> {

    ArrayList<MoyenPaiement> getByFkuserAndAndMoyenType(int fkuser, String type);

}
