package com.example.PayMyBuddy.controllers;


import com.example.PayMyBuddy.services.AmisServices;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/amis")
public class controllerAmis {

    private static final Logger logger = LogManager.getLogger(controllerAmis.class);
    private final AmisServices amisServices;

    public controllerAmis(AmisServices amisServices) {
        this.amisServices = amisServices;
    }


    @PostMapping("/post")
    public void addAmisList(@RequestParam(name = "email")String amis) throws Exception {
        if (amis.equals("")) {
            logger.error("One or more Parameters are missing");
            throw new Exception("Parameters : email, username & Password, are necessary");
        } else {
            logger.info("Add Amis Request sent");
            amisServices.addListAmis(amis);
        }
    }


}
