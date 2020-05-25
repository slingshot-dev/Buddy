package com.example.PayMyBuddy.dao;

import com.example.PayMyBuddy.modeles.User;

public interface IUserDAO {

    void saveUser(User inscription);

    boolean checkUser(String user);

    User getUser(String email);

    void updateUser(User update);

    void deleteUser(String delete);

}
