package com.example.PayMyBuddy.repository;

import com.example.PayMyBuddy.modeles.CB;
import com.example.PayMyBuddy.modeles.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public interface CBRepository extends JpaRepository<CB, Integer> {

    CB findByFkMoyenP(int fkmoyenp);
    CB getByCbNom(String cb);

}
