package com.example.PayMyBuddy.controllers;


import com.example.PayMyBuddy.modeles.User;
import com.example.PayMyBuddy.services.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


/**
 * Controller permettant l'acces et l'authentification de l'utilisateur
 *
 */

@RestController
@RequestMapping("/login")
public class ControllerLogin {

    private static final Logger logger = LogManager.getLogger(ControllerLogin.class);

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public ControllerLogin(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     *
     * @param email : email de l'utilisateur
     * @param pass : Mot de passe de l'utilisateur
     * @param request : Session Http
     * @return : retourne True si les parametres de connexion sont valide.  Utilisateur authentifié.
     */

    @GetMapping
    public boolean login(String email, String pass, HttpServletRequest request) {

        User user = userService.getUserInfos(email);
        boolean login = passwordEncoder.matches(pass, user.getPassword());

        if (login) {
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            logger.info("Acces Authorized");
            logger.info(session.getAttribute("user"));
        }
        else{
            logger.error("Access denied, Password not matching");
        }
        return login;
    }

}
