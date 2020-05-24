package com.example.PayMyBuddy.dao;

import com.example.PayMyBuddy.modeles.CB;
import com.example.PayMyBuddy.modeles.RIB;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RIBRepository extends JpaRepository<RIB, Integer> {

    RIB findByFkMoyenP(int fkmoyenp);
    RIB getByRibNom(String rib);

}
