
package com.example.PayMyBuddy.services;

import com.example.PayMyBuddy.dao.IUserDAO;
import com.example.PayMyBuddy.dao.UserDAO;
import com.example.PayMyBuddy.repository.UserRepository;
import com.example.PayMyBuddy.modeles.User;
import org.springframework.stereotype.Service;


@Service("userService")
public class UserService {

    private final IUserDAO userDAO;
    private final UserRepository userRepository;

    public UserService(UserDAO userDAO, UserRepository userRepository) {
        this.userDAO = userDAO;
        this.userRepository = userRepository;
    }


        public User getUserInfos(String email) {
        return userDAO.getUser(email);
        }

        public void AddUser(User inscription) {
            userDAO.saveUser(inscription);
        }

        public void updateUser(User userUpdate) {
            userUpdate.setIduser(getUserInfos(userUpdate.getEmail()).getIduser());
            userDAO.updateUser(userUpdate);
        }

        public void deleteUser(String email) {
            userDAO.deleteUser(email);
        }

        public User getUser(String email){
        return userRepository.getUSERByEmail(email);
        }
}
