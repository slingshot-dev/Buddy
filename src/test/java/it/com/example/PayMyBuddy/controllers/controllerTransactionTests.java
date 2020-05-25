package it.com.example.PayMyBuddy.controllers;

import com.example.PayMyBuddy.PayMyBuddyApplication;
import com.example.PayMyBuddy.repository.TransactionRepository;
import com.example.PayMyBuddy.modeles.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest(classes = PayMyBuddyApplication.class)
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Sql({"/delete.sql", "/data_test.sql"})
public class controllerTransactionTests {


    @Autowired
    MockMvc mockMvc;
    @Autowired
    TransactionRepository transactionRepository;

    @Test
    public void addTransactionTransfert() throws Exception {
        // Arrange
        MockHttpSession session = new MockHttpSession();
        User user = new User();
        user.setEmail("cyrille@outlook.fr");
        session.setAttribute("user", user);

        // Act
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/transac/post?emailPayeur=cyrille@outlook.fr&emailPaye=fabienne@outlook.fr&montant=100&transacType=Transfert")
                .session(session);
        this.mockMvc.perform(builder)
                .andDo(print())

        // Assert
                .andExpect(MockMvcResultMatchers.status()
                        .isOk());

        assertEquals(transactionRepository.getByTransacMontant(100).getTransacType(), "Transfert");

    }

    @Test
    public void addTransactionFund() throws Exception {
        // Arrange

        MockHttpSession session = new MockHttpSession();
        User user = new User();
        user.setEmail("cyrille@outlook.fr");
        session.setAttribute("user", user);

        // Act
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/transac/post?emailPayeur=cyrille@outlook.fr&emailPaye=buddy@buddy.com&montant=400&transacType=Chargement Fonds")
                .session(session);
        this.mockMvc.perform(builder)
                .andDo(print())

        // Assert
                .andExpect(MockMvcResultMatchers.status()
                        .isOk());

        assertEquals(transactionRepository.getByTransacMontant(400).getTransacType(), "Chargement Fonds");

    }


    @Test
    public void getBalanceUser() throws Exception {
        // Arrange
        MockHttpSession session = new MockHttpSession();
        User user = new User();
        user.setEmail("cyrille@outlook.fr");
        session.setAttribute("user", user);

        // Act
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/transac/balance?email=cyrille@outlook.fr")
                .session(session);
        MvcResult result =  this.mockMvc.perform(builder)
                .andDo(print())

         // Assert
                .andExpect(MockMvcResultMatchers.status()
                        .isOk())
                        .andReturn();
        assertEquals(result.getResponse().getContentAsString(), "500.0");

    }

    @Test
    public void getBalanceSociete() throws Exception {
        // Arrange
        MockHttpSession session = new MockHttpSession();
        User user = new User();
        user.setEmail("buddy@buddy.com");
        session.setAttribute("user", user);

        // Act
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/transac/balancesociete?email=buddy@buddy.com")
                .session(session);
        MvcResult result = this.mockMvc.perform(builder)
                .andDo(print())

        // Assert
                .andExpect(MockMvcResultMatchers.status()
                        .isOk())
                .andReturn();
        assertEquals(result.getResponse().getContentAsString(), "500.0");


    }

}
