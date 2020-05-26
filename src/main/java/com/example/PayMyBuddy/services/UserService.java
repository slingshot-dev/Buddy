
package com.example.PayMyBuddy.services;

import com.example.PayMyBuddy.dao.IUserDAO;
import com.example.PayMyBuddy.dao.UserDAO;
import com.example.PayMyBuddy.repository.UserRepository;
import com.example.PayMyBuddy.modeles.User;
import org.springframework.stereotype.Service;

/**
 * Service permettant :
 * - La recuperation d'infos d'un utilisateur
 * - L'ajout d'un utilisateur
 * - La mise a jour des infos d'un utilisateur
 * - La suppression d'un utilisateur
 */

@Service("userService")
public class UserService {

    private final IUserDAO userDAO;
    private final UserRepository userRepository;

    public UserService(UserDAO userDAO, UserRepository userRepository) {
        this.userDAO = userDAO;
        this.userRepository = userRepository;
    }

    /**
     * @param email : Email de l'utilisateur
     * @return : Retourne les informations de cet utilisateur
     */

    public User getUserInfos(String email) {
        return userDAO.getUser(email);
    }


    /**
     * @param inscription : Infos de l'utilisateur a ajouter
     */

    public void addUser(User inscription) {
        userDAO.saveUser(inscription);
    }


    /**
     *
     * @param userUpdate : Infos de l'utilisateur a mettre a jour
     */

    public void updateUser(User userUpdate) {
        userUpdate.setIduser(getUserInfos(userUpdate.getEmail()).getIduser());
        userDAO.updateUser(userUpdate);
    }


    /**
     *
     * @param email : Email de l'utilisateur a supprimer
     */

    public void deleteUser(String email) {
        userDAO.deleteUser(email);
    }


    /**
     *
     * @param email : Email de l'utilisateur
     * @return : Retourne les informations d el'utilisateur
     */

    public User getUser(String email) {
        return userRepository.getUSERByEmail(email);
    }
}
