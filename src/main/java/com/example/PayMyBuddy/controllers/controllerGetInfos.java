package com.example.PayMyBuddy.controllers;

import com.example.PayMyBuddy.modeles.User;
import com.example.PayMyBuddy.dao.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;



@RestController
@RequestMapping("/usertest")
public class controllerGetInfos {

    @Autowired
    UserRepository userRepository;

    private static final Logger logger = LogManager.getLogger(controllerGetInfos.class);

    @GetMapping("/all")
    public List<User> getAllUsers() {
        // This returns a JSON or XML with the users
        return userRepository.findAll();
    }


}
