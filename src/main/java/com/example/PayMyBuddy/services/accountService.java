package com.example.PayMyBuddy.services;

import com.example.PayMyBuddy.dao.*;
import com.example.PayMyBuddy.modeles.AccountFull;
import com.example.PayMyBuddy.modeles.CB;
import com.example.PayMyBuddy.modeles.MoyenPaiement;
import com.example.PayMyBuddy.modeles.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.ArrayList;

@Service
public class accountService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    MoyenPaiementDAO moyenPaiementDAO;
    @Autowired
    CBDao cbDao;
    @Autowired
    CBRepository cbRepository;
    @Autowired
    RIBRepository ribRepository;
    @Autowired
    MoyenPaiementRepository moyenPaiementRepository;
    @Autowired
    RIBDao ribDao;



    public void addAcountService (AccountFull account){

        String moyenType = account.getMoyenType();
        int result = 0;
        User user = userRepository.getUSERByEmail(account.getUserMail());

        switch (moyenType) {
            case "CB":
                account.setFkUser(user.getIduser());
                result = moyenPaiementDAO.addMoyenPaiement(account);
                cbDao.addCB(account, result);
                break;
            case "RIB":
                account.setFkUser(user.getIduser());
                result = moyenPaiementDAO.addMoyenPaiement(account);
                ribDao.addRIB(account, result);
                break;
        }
    }

    public void deleteCBService (String emailuser, String cbnom){

        int iduser = userRepository.getUSERByEmail(emailuser).getIduser();
        ArrayList<MoyenPaiement> idmoyenp = moyenPaiementRepository.getByFkuserAndAndMoyenType(iduser, "CB");

        idmoyenp.forEach(moyenpaie -> {

            int test = moyenpaie.getIdmoyenPaie();
            String cbnom2 = cbRepository.findByFkMoyenP(test).getCbNom();
            int idcb = cbRepository.findByFkMoyenP(test).getIdcb();
            if( cbnom.equals(cbnom2) ){
                cbDao.deleteCB(idcb);
                moyenPaiementDAO.deleteMoyrenP(test);
            }
        });
    }

    public void deleteRIBService (String emailuser, String ribnom){

        int iduser = userRepository.getUSERByEmail(emailuser).getIduser();
        ArrayList<MoyenPaiement> idmoyenp = moyenPaiementRepository.getByFkuserAndAndMoyenType(iduser, "RIB");

        idmoyenp.forEach(moyenpaie -> {

            int idmp = moyenpaie.getIdmoyenPaie();
            String cbnom2 = ribRepository.findByFkMoyenP(idmp).getRibNom();
            int idrib = ribRepository.findByFkMoyenP(idmp).getIdrib();
            if( ribnom.equals(cbnom2) ){
                ribDao.deleteRIB(idrib);
                moyenPaiementDAO.deleteMoyrenP(idmp);
            }
        });
    }


}
