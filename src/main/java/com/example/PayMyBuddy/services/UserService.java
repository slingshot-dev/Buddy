
package com.example.PayMyBuddy.services;

import com.example.PayMyBuddy.dao.UserDAO;
import com.example.PayMyBuddy.dao.UserRepository;
import com.example.PayMyBuddy.modeles.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class UserService {

    private final UserDAO userDAO;
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

            userDAO.updateUser(userUpdate);
        }

        public void deleteUser(String email) {

            userDAO.deleteUser(email);
        }

        public List<User> getAllUser() {

            return userRepository.findAll();
        }

        public User getUser(String email){

        return userRepository.getUSERByEmail(email);

        }
}
