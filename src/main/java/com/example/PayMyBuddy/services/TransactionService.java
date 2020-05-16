package com.example.PayMyBuddy.services;

import com.example.PayMyBuddy.constants.ServicesConstants;
import com.example.PayMyBuddy.dao.TransactionDAO;
import com.example.PayMyBuddy.modeles.TransacEmail;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

    private final TransactionDAO transactionDAO;

    public TransactionService(TransactionDAO transactionDAO, TransacEmail transacEmail) {
        this.transactionDAO = transactionDAO;
    }


/*    public TRANSACTIONS getTransacInfos(int transacid) {
        return transactionDAO.g(email);
    }*/

    public void addTransac(TransacEmail transactions) {

        if (transactions.getTransacType().equals("Transfert")) {
            double restMontant = transactions.getMontant() * ServicesConstants.CASH_RATE;
            double prelevement = transactions.getMontant() - restMontant;

            transactions.setMontant(restMontant);
            transactions.setPrelevement(prelevement);
        }
        else {
            transactions.setPrelevement(0);
        }

        transactionDAO.addTransaction(transactions);

    }


}
