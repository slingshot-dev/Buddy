package com.example.PayMyBuddy.services;

import com.example.PayMyBuddy.dao.*;
import com.example.PayMyBuddy.modeles.AccountFull;
import com.example.PayMyBuddy.modeles.MoyenPaiement;
import com.example.PayMyBuddy.modeles.User;
import org.springframework.stereotype.Service;
import java.sql.SQLException;
import java.util.ArrayList;

@Service
public class accountService {

    private final UserRepository userRepository;
    private final MoyenPaiementDAO moyenPaiementDAO;
    private final CBDao cbDao;
    private final CBRepository cbRepository;
    private final RIBRepository ribRepository;
    private final MoyenPaiementRepository moyenPaiementRepository;
    private final RIBDao ribDao;

    public accountService(UserRepository userRepository, MoyenPaiementDAO moyenPaiementDAO, CBDao cbDao, CBRepository cbRepository, RIBRepository ribRepository, MoyenPaiementRepository moyenPaiementRepository, RIBDao ribDao) {
        this.userRepository = userRepository;
        this.moyenPaiementDAO = moyenPaiementDAO;
        this.cbDao = cbDao;
        this.cbRepository = cbRepository;
        this.ribRepository = ribRepository;
        this.moyenPaiementRepository = moyenPaiementRepository;
        this.ribDao = ribDao;
    }


    public void addAcountService (AccountFull account) throws SQLException {

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
                account.setFkUser(user.getIduser()); // Ajout du RIB en mode Transactionnel
                moyenPaiementDAO.addMoyenPaiementTransac(account);
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
