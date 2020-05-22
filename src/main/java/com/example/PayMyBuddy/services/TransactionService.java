package com.example.PayMyBuddy.services;

import com.example.PayMyBuddy.constants.ServicesConstants;
import com.example.PayMyBuddy.dao.TransactionDAO;
import com.example.PayMyBuddy.modeles.TransacEmail;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

    private static final Logger logger = LogManager.getLogger(TransactionService.class);
    private final TransactionDAO transactionDAO;

    public TransactionService(TransactionDAO transactionDAO) {
        this.transactionDAO = transactionDAO;
    }


    public void addTransac(TransacEmail transactions) {

        switch(transactions.getTransacType()) {
            case "Transfert":
                double restMontant = transactions.getMontant() * ServicesConstants.CASH_RATE;
                double prelevement = transactions.getMontant() - restMontant;
                transactions.setMontantPaye(restMontant);
                transactions.setPrelevement(prelevement);
                transactionDAO.addTransaction(transactions);

                break;
            case "Chargement Fonds":
                transactions.setMontantPaye(transactions.getMontant());
                transactions.setPrelevement(0);
                transactionDAO.addTransaction(transactions);
                logger.info("Chargement Fonds");
                break;

            case "Chargement Banque":
                transactions.setMontantPaye(transactions.getMontant());
                transactions.setPrelevement(0);
                transactionDAO.addTransaction(transactions);
                logger.info("Chargement Banque");
                break;
        }
    }
}
