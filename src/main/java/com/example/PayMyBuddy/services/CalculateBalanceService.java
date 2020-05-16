package com.example.PayMyBuddy.services;

import com.example.PayMyBuddy.constants.ServicesConstants;
import com.example.PayMyBuddy.dao.TransactionDAO;
import org.springframework.stereotype.Service;

@Service
public class CalculateBalanceService {

    private final UserService userService;
    private final TransactionDAO transactionDAO;

    public CalculateBalanceService(UserService userService, TransactionDAO transactionDAO) {
        this.userService = userService;
        this.transactionDAO = transactionDAO;
    }


    public float calculateBalance (String email){

        int userBuddy = userService.getUserInfos(ServicesConstants.BuddyEmail).getIduser();
        int user = userService.getUserInfos(email).getIduser();


        return transactionDAO.calculateTransaction(user, userBuddy);
    }


}
