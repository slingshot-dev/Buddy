package com.example.PayMyBuddy.services;

import com.example.PayMyBuddy.constants.ServicesConstants;
import com.example.PayMyBuddy.dao.ITransactionDAO;
import com.example.PayMyBuddy.dao.TransactionDAO;
import com.example.PayMyBuddy.modeles.TransacEmail;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

/**
 * Service d'ajout d'une transaction d'argent. Permet le Transfet d'un utilisateur vers un autre utilisateur.
 * Permet le Chargement et le solde d'un compte Utilisateur
 */

@Service
public class TransactionService {

    private static final Logger logger = LogManager.getLogger(TransactionService.class);
    private final ITransactionDAO transactionDAO;

    public TransactionService(TransactionDAO transactionDAO) {
        this.transactionDAO = transactionDAO;
    }

    /**
     *
     * @param transactions : Informations de la Transaction
     * @return
     */

    public TransacEmail addTransac(TransacEmail transactions) {

        switch(transactions.getTransacType()) {
            case "Transfert":
                double restMontant = Math.round((transactions.getMontant() * ServicesConstants.FEE_RATE)*(1.0 / 0.01)) / (1.0 / 0.01);
                double prelevement = Math.round((transactions.getMontant() - restMontant)*(1.0 / 0.01)) / (1.0 / 0.01);
                transactions.setMontantPaye(restMontant);
                transactions.setPrelevement(prelevement);
                transactionDAO.addTransaction(transactions);
                return transactions;
/*                break;*/

            case "Chargement Fonds":
                transactions.setMontantPaye(transactions.getMontant());
                transactions.setPrelevement(0);
                transactionDAO.addTransaction(transactions);
                logger.info("Chargement Fonds");
                return transactions;
/*                break;*/

            case "Chargement Banque":
                transactions.setMontantPaye(transactions.getMontant());
                transactions.setPrelevement(0);
                transactionDAO.addTransaction(transactions);
                logger.info("Chargement Banque");
                return transactions;
/*                break;*/
            default :
                logger.error("Invalide Type de Transfert");
        }
    return transactions;
    }
}
