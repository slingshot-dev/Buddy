package com.example.PayMyBuddy.dao;

import com.example.PayMyBuddy.modeles.TransacEmail;

public interface ITransactionDAO {

    void addTransaction(TransacEmail transactions);

    double calculateTransaction(int user, int userBuddy);

    double calculateBalanceFonds();

    double calculateBalancePrelev();

}
