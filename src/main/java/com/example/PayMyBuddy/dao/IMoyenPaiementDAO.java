package com.example.PayMyBuddy.dao;

import com.example.PayMyBuddy.modeles.AccountFull;

import java.sql.SQLException;

public interface IMoyenPaiementDAO {

    int addMoyenPaiement(AccountFull account);

    void deleteMoyrenP(int moyenp);

    void addMoyenPaiementTransac(AccountFull account) throws SQLException;

}
