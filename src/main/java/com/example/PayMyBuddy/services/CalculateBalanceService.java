package com.example.PayMyBuddy.services;

import com.example.PayMyBuddy.constants.ServicesConstants;
import com.example.PayMyBuddy.dao.ITransactionDAO;
import com.example.PayMyBuddy.dao.TransactionDAO;
import org.springframework.stereotype.Service;

/**
 * Service de calcul des soldes des utilisateurs et de la Societe
 */

@Service
public class CalculateBalanceService {

    private final UserService userService;
    private final ITransactionDAO transactionDAO;

    public CalculateBalanceService(UserService userService, TransactionDAO transactionDAO) {
        this.userService = userService;
        this.transactionDAO = transactionDAO;
    }


    /**
     *
     * @param email : Email de l'utilisateur
     * @return : Retourne le Solde de son compte utilisateur
     */

    public double calculateBalance (String email){

        int userBuddy = userService.getUserInfos(ServicesConstants.BuddyEmail).getIduser();
        int user = userService.getUserInfos(email).getIduser();
        return transactionDAO.calculateTransaction(user, userBuddy);
    }

    /**
     *
     * @return : retour le solde du compte de la societe
     */

    public double calculateBalanceBuddy (){
        return transactionDAO.calculateBalancePrelev() + transactionDAO.calculateBalanceFonds();
    }

}
