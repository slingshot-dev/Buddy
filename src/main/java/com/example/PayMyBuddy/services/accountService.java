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
}


/*        // inserer CB dans table cb
        cbDao.addCB(newcb);

        // inserer type moyen de paiement dans table moyen_paiement
        MoyenPaiement moyenPaiement = new MoyenPaiement();
        moyenPaiement.setMoyenType("CB");
        moyenPaiementDAO.addMoyenPaiement(moyenPaiement);

        // linker moyen et CB


        // recuperer iduser et linker a Moyen
        int iduser = userRepository.getUSERByEmail("toto@outlook.fr").getIduser();*/

/*        MoyenPaiement moyenPaiement = new MoyenPaiement();
        moyenPaiement.setMoyenType(account.getMoyenType());
        moyenPaiement.setFkuser(account.getFkuser());

        moyenPaiementRepository.save(moyenPaiement);

        CB cb = new CB();
        cb.setCbNom(account.getCbNom());
        cb.setCbNumber(account.getCbNumber());
        cb.setCbValide(account.getCbValide());*/
/*        cb.setFkMoyenP(select LAST_INSERT_ID() from moyen_paiement);*/

/*        int result = moyenPaiementDAO.getLastID();
        cb.setFkMoyenP(result);

        cbRepository.save(cb);*/