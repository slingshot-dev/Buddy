package com.example.PayMyBuddy.controllers;

import com.example.PayMyBuddy.modeles.TransacEmail;
import com.example.PayMyBuddy.services.CalculateBalanceService;
import com.example.PayMyBuddy.services.TransactionService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/transac")
public class controllerTransaction {

    private static final Logger logger = LogManager.getLogger(controllerUserPPD.class);
    private final TransactionService transactionService;
    private final CalculateBalanceService calculateBalanceService;

    public controllerTransaction(TransactionService transactionService, CalculateBalanceService calculateBalanceService) {
        this.transactionService = transactionService;
        this.calculateBalanceService = calculateBalanceService;
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
        public float getBalance(String email) throws Exception {

        if (email.isEmpty()) {
            logger.error("One or more Parameters are missing");
            throw new Exception("Parameter : email, is necessary");
        } else {
            logger.info("GetBalance Request sent");
            return calculateBalanceService.calculateBalance(email);
        }
    }

}
