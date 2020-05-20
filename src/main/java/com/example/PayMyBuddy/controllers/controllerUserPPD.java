package com.example.PayMyBuddy.controllers;

import com.example.PayMyBuddy.dao.CBDao;
import com.example.PayMyBuddy.dao.UserRepository;
import com.example.PayMyBuddy.modeles.AccountFull;
import com.example.PayMyBuddy.modeles.User;
import com.example.PayMyBuddy.services.UserService;
import com.example.PayMyBuddy.services.accountService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;


@RestController
@RequestMapping("/user")
public class controllerUserPPD {



    private static final Logger logger = LogManager.getLogger(controllerUserPPD.class);
    private final UserService userService;
    private final accountService accountService;
    private final UserRepository userRepository;

    public controllerUserPPD(UserService userService, CBDao cbDao, com.example.PayMyBuddy.services.accountService accountService, UserRepository userRepository) {
        this.userService = userService;
        this.accountService = accountService;
        this.userRepository = userRepository;
    }


    @GetMapping
    public User getUser(String email) throws Exception {
        if (email.isEmpty()) {
            logger.error("One or more Parameters are missing");
            throw new Exception("Parameter : email, is necessary");
        } else {
            logger.info("GetInfos User Request sent");
            return userService.getUser(email);
        }
    }

    @GetMapping("/all")
    public List<User> getAllUsers() {
        // This returns a JSON or XML with the users
        return userRepository.findAll();
    }

    @PostMapping("/post")
    public void addUser(User inscription) throws Exception {
        if (inscription.getEmail().isEmpty() || inscription.getNom().isEmpty() || inscription.getPassword().isEmpty()) {
            logger.error("One or more Parameters are missing");
            throw new Exception("Parameters : email, username & Password, are necessary");
        } else {
            logger.info("Add User request sent");
            userService.AddUser(inscription);
        }
    }

    @PutMapping("/put")
    public void updateUser(User updateUser, HttpServletRequest request) throws Exception {
        if (updateUser.getEmail().isEmpty() || updateUser.getNom().isEmpty() || updateUser.getPrenom().isEmpty() || updateUser.getPassword().isEmpty()) {
            logger.error("One or more Parameters Firstname, Lastname, Address, City, Zip, Phone & Email are missing");
            throw new Exception("Parameters : Firstname, Lastname, Email, are necessary");
        } else {
            HttpSession session = request.getSession();

            // recuperation de la session ne cours
            User user = (User) session.getAttribute("user");

            // verification si le user est bien connecté et lancement de la requete si email ok
            String email = updateUser.getEmail();
            if (user != null && email.equals(user.getEmail())) {
                logger.info("Update User request sent");
                userService.updateUser(updateUser);
            } else {
                logger.error("user not authenticated");
            }
        }
    }

    @DeleteMapping("/delete")
    public void deleteUser(String email, HttpServletRequest request) throws Exception {
        if (email.isEmpty()) {
            logger.error("Parameter email is missing");
            throw new Exception("Parameter : email is necessary");
        } else {

            HttpSession session = request.getSession();

            // recuperation de la session ne cours
            User user = (User) session.getAttribute("user");

            // verification si le user est bien connecté et lancement de la requete si email ok
            if (user != null && email.equals(user.getEmail())) {
                logger.info("Delete Uset request sent");
                userService.deleteUser(email);
            } else {
                logger.error("user not authenticated");
            }
        }
    }

    @PostMapping("/createaccount")
    public void addAccount(AccountFull account, HttpServletRequest request) throws Exception {
        if (account.getMoyenType().isEmpty() || account.getUserMail().isEmpty()) {
            logger.error("One or more Parameters are missing");
            throw new Exception("Parameters : nom, numero et validité, are necessary");
        } else {
            HttpSession session = request.getSession();

            // recuperation de la session ne cours
            User user = (User) session.getAttribute("user");

            // verification si le user est bien connecté et lancement de la requete si email ok
            String email = account.getUserMail();
            if (user != null && email.equals(user.getEmail())) {
                logger.info("Add Account request sent");
                accountService.addAcountService(account);
            } else {
                logger.error("user not authenticated");
            }
        }
    }

    @DeleteMapping("/deletecb")
    public void deleteCB(@RequestParam(name = "emailuser")String user, @RequestParam(name = "cbnom")String cbnom, HttpServletRequest request) throws Exception {
        if (user.equals("")) {
            logger.error("One or more Parameters are missing");
            throw new Exception("Parameters : email are necessary");
        } else {
            HttpSession session = request.getSession();

            // recuperation de la session ne cours
            User user2 = (User) session.getAttribute("user");

            // verification si le user est bien connecté et lancement de la requete si email ok
            if (user2 != null && user.equals(user2.getEmail())) {
                logger.info("Delete Amis Request sent");
                accountService.deleteCBService(user, cbnom);
            } else {
                logger.error("user not authenticated");
            }
        }
    }

    @DeleteMapping("/deleterib")
    public void deleteRib(@RequestParam(name = "emailuser")String user, @RequestParam(name = "ribnom")String ribnom, HttpServletRequest request) throws Exception {
        if (user.equals("")) {
            logger.error("One or more Parameters are missing");
            throw new Exception("Parameters : email are necessary");
        } else {

            HttpSession session = request.getSession();

            // recuperation de la session ne cours
            User user2 = (User) session.getAttribute("user");

            // verification si le user est bien connecté et lancement de la requete si email ok
            if (user2 != null && user.equals(user2.getEmail())) {
                logger.info("Delete Amis Request sent");
                accountService.deleteRIBService(user, ribnom);
            } else {
                logger.error("user not authenticated");
            }
        }
    }
}


