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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = PayMyBuddyApplication.class)
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
public class controllerTransactionTests {


    @Autowired
    MockMvc mockMvc;
    @Autowired
    UserDAO userDAO;

    @Test
    public void addTransactionTransfert() throws Exception {
        // Arrange & Act
        this.mockMvc.perform(post("/transac/post?emailPayeur=cyrille@outlook.fr&emailPaye=fabienne@outlook.fr&montant=100&transacType=Transfert"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("")));
    }

    @Test
    public void addTransactionFund() throws Exception {
        // Arrange & Act
        this.mockMvc.perform(post("/transac/post?emailPayeur=cyrille@outlook.fr&emailPaye=buddy@outlook.fr&montant=500&transacType=Chargement Fonds"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("")));
    }


    @Test
    public void getBalance() throws Exception {
        // Arrange & Act
        MvcResult mvcResult = this.mockMvc.perform(post("/amis/post?emailuser=cyrille@outlook.fr&emailamis=fabienne@outlook.fr"))
                .andDo(print()).andReturn();
        MvcResult mvcResult2 = this.mockMvc.perform(delete("/amis/delete/?emailuser=cyrille@outlook.fr&emailamis=fabienne@outlook.fr"))
                .andDo(print()).andReturn();
        // Assert
        int status = mvcResult2.getResponse().getStatus();
        assertEquals(200, status);
    }


}
