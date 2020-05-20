package it.com.example.PayMyBuddy.controllers;

import com.example.PayMyBuddy.PayMyBuddyApplication;
import com.example.PayMyBuddy.dao.UserDAO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest(classes = PayMyBuddyApplication.class)
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
public class controllerUserPPDTests {


    @Autowired
    MockMvc mockMvc;
    @Autowired
    UserDAO userDAO;


    @Test
    public void getUser() throws Exception {
        // Arrange & Act
        this.mockMvc.perform(get("/user?email=cyrille@outlook.fr"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Guillet")));
    }

    @Test
    public void addUser() throws Exception {
        // Arrange & Act
        MvcResult mvcResult = this.mockMvc.perform(post("/user/post?email=leane@outlook.fr&nom=Guillet&prenom=Leane&password=test&role=User"))
                .andDo(print()).andReturn();
        // Assert
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        assertEquals(userDAO.getUser("leane@outlook.fr").getPrenom(), "Leane");

    }

    @Test
    public void updateUser() throws Exception {
        // Arrange & Act
        MvcResult mvcResult = this.mockMvc.perform(put("/user/put/?email=Cyrille@outlook.fr&nom=Guillet&prenom=Cyrille2&password=test&iduser=1&role=User"))
                .andDo(print()).andReturn();
        // Assert
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        assertEquals(userDAO.getUser("cyrille@outlook.fr").getPrenom(), "Cyrille2");

    }

    @Test
    public void deleteUser() throws Exception {
        // Arrange & Act
        MvcResult mvcResult = this.mockMvc.perform(post("/user/post?email=test@outlook.fr&nom=TestNom&prenom=TestsPrenom&password=test&role=User"))
                .andDo(print()).andReturn();
        MvcResult mvcResult2 = this.mockMvc.perform(delete("/user/delete/?email=test@outlook.fr"))
                .andDo(print()).andReturn();
        // Assert
        int status = mvcResult2.getResponse().getStatus();
        assertEquals(200, status);
    }

}
