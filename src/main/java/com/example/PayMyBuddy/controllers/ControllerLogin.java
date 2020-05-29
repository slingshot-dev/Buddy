package com.example.PayMyBuddy.controllers;


import com.example.PayMyBuddy.modeles.User;
import com.example.PayMyBuddy.services.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

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
     * @param user : Objet User transmis dans le Body de la requete : Infos utilisateur Email, Password.
     * @param request : Session http
     * @return
     */

    @GetMapping
        public boolean login(@RequestBody User user, HttpServletRequest request) {

        User userCheck = userService.getUserInfos(user.getEmail());
        boolean login = passwordEncoder.matches(user.getPassword(), userCheck.getPassword());

        if (login) {
            HttpSession session = request.getSession();
            session.setAttribute("user", userCheck);
            logger.info("Acces Authorized");
            logger.info(session.getAttribute("user"));
        }
        else{
            logger.error("Access denied, Password not matching");
        }
        return login;
    }

}
