package com.example.PayMyBuddy.services;

import com.example.PayMyBuddy.dao.AmisDAO;
import com.example.PayMyBuddy.dao.AmisRepository;
import com.example.PayMyBuddy.dao.UserRepository;
import com.example.PayMyBuddy.modeles.Amis;
import com.example.PayMyBuddy.modeles.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class AmisServices {

    private static final Logger logger = LogManager.getLogger(AmisServices.class);
    private final AmisDAO amisDAO;
    private final UserService userService;
    private final UserRepository userRepository;
    private final AmisRepository amisRepository;

    public AmisServices(AmisDAO amisDAO, UserService userService, UserRepository userRepository, AmisRepository amisRepository) {
        this.amisDAO = amisDAO;
        this.userService = userService;
        this.userRepository = userRepository;
        this.amisRepository = amisRepository;
    }


    public void addListAmis(String user, String amis) {

        // Recuperer information sur le User a ajouter dans la liste amis
        User resultInfosUser = userService.getUserInfos(amis);

        // Verifier si ce User est dans la Base de données
        if (resultInfosUser.getIduser() == 0) {
            logger.error("User is not registered in Database");
        } else {
            int amisAmis = resultInfosUser.getIduser();
            int amisUser = userService.getUserInfos(user).getIduser();

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

    public void deleteAmisList (String emailUser, String emailAmis) {

        // Recuperer iduser amis en fonction email
        int iduserAmis = userRepository.getUSERByEmail(emailAmis).getIduser();
        int iduserUser = userRepository.getUSERByEmail(emailUser).getIduser();

        // Recuperer pk_amis
        int idAmis = amisRepository.getAmisByAmisAmisAndAmisUser(iduserAmis, iduserUser).getIdamis();

        // Delete amis de la liste
        amisDAO.deleteAmis(idAmis);
    }

    public List<User> getAmisList (String emailUser) {

        ArrayList<User> resultListAmisEmail = new ArrayList<>();
        // Recuperer iduser amis en fonction email
        int iduserUser = userRepository.getUSERByEmail(emailUser).getIduser();

        ArrayList<Amis> resultListAmis = amisRepository.getAmisByAmisUser(iduserUser);

        resultListAmis.forEach(user ->{

            User amis = userRepository.getUserByIduser(user.getAmisAmis());
            resultListAmisEmail.add(amis);


        });


        return resultListAmisEmail;

    }

}
