package com.example.PayMyBuddy.Services;

import com.example.PayMyBuddy.PayMyBuddyApplication;
import com.example.PayMyBuddy.dao.TransactionDAO;
import com.example.PayMyBuddy.modeles.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import java.util.ResourceBundle;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = PayMyBuddyApplication.class)
@Sql({"/Create_BDD.sql", "/data_test.sql"})
public class TransactionServiceTest {

    ResourceBundle bundle = ResourceBundle.getBundle("TestResources");

    @Mock
    private static TransactionDAO transactionDAO;

    @Autowired
    MockMvc mockMvc;


    @BeforeEach
    public void setup() throws Exception {
        this.mockMvc.perform(get("/login").contentType(MediaType.APPLICATION_JSON).content(bundle.getString("login")));
    }



    @Test
    public void calculateRateTransactionTransfert() throws Exception {
        // Arrange
        MockHttpSession session = new MockHttpSession();
        User user = new User();
        user.setEmail("cyrille@outlook.fr");
        session.setAttribute("user", user);

        // Act
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/transac/post?emailPayeur=cyrille@outlook.fr&emailPaye=fabienne@outlook.fr&montant=52.6&transacType=Transfert")
                .session(session);
        this.mockMvc.perform(builder)
                .andDo(print())
                // Assert
                .andExpect(content().string(containsString("0.26")))
                .andExpect(jsonPath("$.montant").value("52.6"))
                .andExpect(jsonPath("$.montantPaye").value("52.34"))
                .andExpect(MockMvcResultMatchers.status()
                        .isOk());
    }


    @Test
    public void calculateRateTransactionRefund() throws Exception {
        // Arrange
        MockHttpSession session = new MockHttpSession();
        User user = new User();
        user.setEmail("cyrille@outlook.fr");
        session.setAttribute("user", user);

        // Act
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/transac/post?emailPayeur=buddy@buddy.com&emailPaye=cyrille@outlook.fr&montant=200&transacType=Chargement Banque")
                .session(session);
        this.mockMvc.perform(builder)
                .andDo(print())
                // Assert
                .andExpect(content().string(containsString("0.0")))
                .andExpect(jsonPath("$.montant").value("200.0"))
                .andExpect(jsonPath("$.montantPaye").value("200.0"))
                .andExpect(MockMvcResultMatchers.status()
                        .isOk());
    }


    @Test
    public void calculateRateTransactionFund() throws Exception {
        // Arrange
        MockHttpSession session = new MockHttpSession();
        User user = new User();
        user.setEmail("cyrille@outlook.fr");
        session.setAttribute("user", user);

        // Act
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/transac/post?emailPayeur=fabienne@outlook.fr&emailPaye=buddy@buddy.com&montant=1000&transacType=Chargement Fonds")
                .session(session);
        this.mockMvc.perform(builder)
                .andDo(print())
                // Assert
                .andExpect(content().string(containsString("0.0")))
                .andExpect(jsonPath("$.montant").value("1000.0"))
                .andExpect(jsonPath("$.montantPaye").value("1000.0"))
                .andExpect(MockMvcResultMatchers.status()
                        .isOk());
    }


}
