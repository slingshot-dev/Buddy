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

/**
 * Controller permettant l'ajout d'une Transaction, de recuperer le solde d'un compte utilisateur et celui de la societe.
 */

@RestController
@RequestMapping("/transac")
public class ControllerTransaction {

    private static final Logger logger = LogManager.getLogger(ControllerUserPPD.class);
    private final TransactionService transactionService;
    private final CalculateBalanceService calculateBalanceService;

    public ControllerTransaction(TransactionService transactionService, CalculateBalanceService calculateBalanceService, UserService userService) {
        this.transactionService = transactionService;
        this.calculateBalanceService = calculateBalanceService;
    }

    /**
     * @param transactions : informations de la Transaction a realiser
     * @throws Exception : Exception si parametres incorrect ou manquant.
     * @return
     */

    @PostMapping("/post")
    public TransacEmail addTransac(TransacEmail transactions) throws Exception {
        if (transactions.getMontant() == 0 || transactions.getEmailPaye().isEmpty() || transactions.getEmailPayeur().isEmpty()) {
            logger.error("One or more Parameters are missing");
            throw new Exception("Parameters : emails et montant, are necessary");
        } else {
            logger.info("Add Transaction request sent");
            return transactionService.addTransac(transactions);
        }
    }

    /**
     *
     * @param email : Email de l'utilisateur
     * @param request : Session Http
     * @return : retourne la valeur du solde
     * @throws Exception : Exception si parametres incorrect ou manquant.
     */

    @GetMapping("/balance")
    public double getBalance(String email, HttpServletRequest request) throws Exception {

        double valeur = 0;

        if (email.isEmpty()) {
            logger.error("parameter email is necessary");
            throw new Exception("Parameter : email, is necessary");
        } else {
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

    /**
     *
     * @param email : email de l'utilisateur
     * @param request : session http
     * @return : reourne le solde d ela societe.
     */

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
