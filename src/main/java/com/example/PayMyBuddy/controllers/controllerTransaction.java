package com.example.PayMyBuddy.controllers;

import com.example.PayMyBuddy.modeles.TransacEmail;
import com.example.PayMyBuddy.modeles.User;
import com.example.PayMyBuddy.services.CalculateBalanceService;
import com.example.PayMyBuddy.services.TransactionService;
import com.example.PayMyBuddy.services.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@RestController
@RequestMapping("/transac")
public class controllerTransaction {

    private static final Logger logger = LogManager.getLogger(controllerUserPPD.class);
    private final TransactionService transactionService;
    private final CalculateBalanceService calculateBalanceService;
    private final UserService userService;

    public controllerTransaction(TransactionService transactionService, CalculateBalanceService calculateBalanceService, UserService userService) {
        this.transactionService = transactionService;
        this.calculateBalanceService = calculateBalanceService;
        this.userService = userService;
    }


        @PostMapping("/post")
        public void addTransac(TransacEmail transactions) throws Exception {
        if (transactions.getMontant() == 0 || transactions.getEmailPaye().isEmpty() || transactions.getEmailPayeur().isEmpty()) {
            logger.error("One or more Parameters are missing");
            throw new Exception("Parameters : emails et montant, are necessary");
        } else {
            logger.info("Add Transaction request sent");
            transactionService.addTransac(transactions);
        }
    }

        @GetMapping("/balance")
        public double getBalance(String email, HttpServletRequest request) throws Exception {

            double valeur = 0;

            if (email.isEmpty()) {
                logger.error("parameter email is necessary");
                throw new Exception("Parameter : email, is necessary");
            }
            else {
                HttpSession session = request.getSession();

                // recuperation de la session ne cours
                User user = (User) session.getAttribute("user");

                // verification si le user est bien connecté et lancement de la requete si email ok
                if (user != null && email.equals(user.getEmail())) {
                    logger.info("GetBalance Request sent");
                    valeur = calculateBalanceService.calculateBalance(email);
                } else {
                    logger.error("user not authenticated");
                }
            }
            return valeur;
    }

    @GetMapping("/balancesociete")
    public double getBalanceSociete(String email, HttpServletRequest request) {

        double valeur = 0;
            HttpSession session = request.getSession();

            // recuperation de la session ne cours
            User user = (User) session.getAttribute("user");

            // verification si le user est bien connecté et lancement de la requete si email ok
            if (user != null && email.equals(user.getEmail())) {
                logger.info("GetBalance Request sent");
                valeur = calculateBalanceService.calculateBalanceBuddy();
            } else {
                logger.error("user not authenticated");
            }
        return valeur;
    }

}
