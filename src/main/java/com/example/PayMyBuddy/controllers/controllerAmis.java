package com.example.PayMyBuddy.controllers;


import com.example.PayMyBuddy.modeles.User;
import com.example.PayMyBuddy.services.AmisServices;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@RestController
@RequestMapping("/amis")
public class controllerAmis {

    private static final Logger logger = LogManager.getLogger(controllerAmis.class);
    private final AmisServices amisServices;

    public controllerAmis(AmisServices amisServices) {
        this.amisServices = amisServices;
    }


    @PostMapping("/post")
    public void addAmisList(@RequestParam(name = "emailuser")String user, @RequestParam(name = "emailamis")String amis, HttpServletRequest request) throws Exception {
        if (amis.equals("")) {
            logger.error("One or more Parameters are missing");
            throw new Exception("Parameters : email, username & Password, are necessary");
        } else {
            HttpSession session = request.getSession();

            // recuperation de la session ne cours
            User user2 = (User) session.getAttribute("user");

            // verification si le user est bien connecté et lancement de la requete si email ok
            if (user2 != null && user.equals(user2.getEmail())) {
                logger.info("Add Amis Request sent");
                amisServices.addListAmis(user, amis);
            } else {
                logger.error("user not authenticated");
            }
        }
    }

    @DeleteMapping("/delete")
    public void deleteAmisList(@RequestParam(name = "emailuser")String user, @RequestParam(name = "emailamis")String amis, HttpServletRequest request) throws Exception {
        if (amis.equals("")) {
            logger.error("One or more Parameters are missing");
            throw new Exception("Parameters : email are necessary");
        } else {
            HttpSession session = request.getSession();

            // recuperation de la session ne cours
            User user2 = (User) session.getAttribute("user");

            // verification si le user est bien connecté et lancement de la requete si email ok
            if (user2 != null && user.equals(user2.getEmail())) {
                logger.info("Delete Amis Request sent");
                amisServices.deleteAmisList(user, amis);
            } else {
                logger.error("user not authenticated");
            }
        }
    }
}
