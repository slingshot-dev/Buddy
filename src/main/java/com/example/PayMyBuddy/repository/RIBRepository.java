package com.example.PayMyBuddy.repository;

import com.example.PayMyBuddy.modeles.RIB;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RIBRepository extends JpaRepository<RIB, Integer> {

    RIB findByFkMoyenP(int fkmoyenp);
    RIB getByRibNom(String rib);

}
