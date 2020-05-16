package com.example.PayMyBuddy.controllers;

import com.example.PayMyBuddy.dao.CBDao;
import com.example.PayMyBuddy.modeles.AccountFull;
import com.example.PayMyBuddy.modeles.CB;
import com.example.PayMyBuddy.modeles.User;
import com.example.PayMyBuddy.services.UserService;
import com.example.PayMyBuddy.services.accountService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class controllerUserPPD {

    private static final Logger logger = LogManager.getLogger(controllerUserPPD.class);
    private final UserService userService;
    private final CBDao cbDao;
    private final accountService accountService;

    public controllerUserPPD(UserService userService, CBDao cbDao, com.example.PayMyBuddy.services.accountService accountService) {
        this.userService = userService;
        this.cbDao = cbDao;
        this.accountService = accountService;
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
    public void updateUser(User updateUser) throws Exception {
        if (updateUser.getEmail().isEmpty() || updateUser.getNom().isEmpty() || updateUser.getPrenom().isEmpty() || updateUser.getPassword().isEmpty()) {
            logger.error("One or more Parameters Firstname, Lastname, Address, City, Zip, Phone & Email are missing");
            throw new Exception("Parameters : Firstname, Lastname, Email, are necessary");
        } else {
            logger.info("Update User request sent");
            userService.updateUser(updateUser);
        }
    }

    @DeleteMapping("/delete")
    public void deleteUser(String email) throws Exception {
        if (email.isEmpty()) {
            logger.error("Parameter email is missing");
            throw new Exception("Parameter : email is necessary");
        } else {
            logger.info("Delete Uset request sent");
            userService.deleteUser(email);
        }
    }

    @PostMapping("/createaccount")
    public void addAccount(AccountFull account) throws Exception {
        if (account.getMoyenType().isEmpty() || account.getUserMail().isEmpty()) {
            logger.error("One or more Parameters are missing");
            throw new Exception("Parameters : nom, numero et validité, are necessary");
        } else {
            logger.info("Add Account request sent");
            accountService.addAcountService(account);
        }
    }

}


