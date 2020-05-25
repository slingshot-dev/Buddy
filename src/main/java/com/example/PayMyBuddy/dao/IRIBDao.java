package com.example.PayMyBuddy.dao;

import com.example.PayMyBuddy.modeles.AccountFull;

public interface IRIBDao {

    void addRIB(AccountFull account, int result);

    void deleteRIB(int rib);

}
