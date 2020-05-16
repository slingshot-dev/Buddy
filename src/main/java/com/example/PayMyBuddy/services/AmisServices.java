package com.example.PayMyBuddy.services;

import com.example.PayMyBuddy.dao.AmisDAO;
import com.example.PayMyBuddy.modeles.Amis;
import com.example.PayMyBuddy.modeles.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class AmisServices {

    private static final Logger logger = LogManager.getLogger(AmisServices.class);
    private final AmisDAO amisDAO;
    private final UserService userService;

    public AmisServices(AmisDAO amisDAO, UserService userService) {
        this.amisDAO = amisDAO;
        this.userService = userService;
    }


    public void addListAmis(String amis) {

        // Recuperer information sur le User a ajouter dans la liste amis
        User resultInfosUser = userService.getUserInfos(amis);

        // Verifier si ce User est dans la Base de données
        if (resultInfosUser.getIduser() == 0) {
            logger.error("User is not registered in Database");
        } else {
            int amisAmis = resultInfosUser.getIduser();
            int amisUser = userService.getUserInfos("toto@outlook.fr").getIduser();

            Amis listAmis = new Amis();

            listAmis.setAmisUser(amisUser);
            listAmis.setAmisAmis(amisAmis);

        // Verfier si User est deja dans a Liste d'amis
            boolean result = amisDAO.checkAmisList(listAmis);

            if (!result) {
                amisDAO.AddList(listAmis); // Si non, on ajoute a la Liste d'amis
                logger.info("User ajouté a la Liste d'Amis");
            } else {
                logger.error("Error : User deja dans la Liste d'Amis"); // Si oui, Erreur
            }

        }


    }


}
