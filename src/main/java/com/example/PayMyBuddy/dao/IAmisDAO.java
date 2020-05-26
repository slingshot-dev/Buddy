package com.example.PayMyBuddy.dao;

import com.example.PayMyBuddy.modeles.Amis;

public interface IAmisDAO {

    void addList(Amis amis);

    boolean checkAmisList(Amis amis);

    void deleteAmis(int delete);

}
