package com.example.PayMyBuddy.dao;

import com.example.PayMyBuddy.modeles.Amis;

public interface IAmisDAO {

    void AddList(Amis amis);

    boolean checkAmisList(Amis amis);

    void deleteAmis(int delete);

}
