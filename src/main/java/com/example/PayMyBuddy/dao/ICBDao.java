package com.example.PayMyBuddy.dao;

import com.example.PayMyBuddy.modeles.AccountFull;

public interface ICBDao {

    void addCB(AccountFull account, int result);

    void deleteCB(int cb);

}
