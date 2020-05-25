package it.com.example.PayMyBuddy.controllers;

import com.example.PayMyBuddy.PayMyBuddyApplication;
import com.example.PayMyBuddy.repository.CBRepository;
import com.example.PayMyBuddy.repository.RIBRepository;
import com.example.PayMyBuddy.dao.UserDAO;
import com.example.PayMyBuddy.modeles.User;
import org.junit.jupiter.api.BeforeEach;
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

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest(classes = PayMyBuddyApplication.class)
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Sql({"/delete.sql", "/data_test.sql"})
public class controllerUserPPDTests {



    @Autowired
    MockMvc mockMvc;
    @Autowired
    UserDAO userDAO;
    @Autowired
    RIBRepository ribRepository;
    @Autowired
    CBRepository cbRepository;

    @BeforeEach
    public void setup() throws Exception {
        this.mockMvc.perform(get("/login?email=cyrille@outlook.fr&pass=rsv1000r"));
    }

    @Test
    public void getUser() throws Exception {

        // Arrange & Act
        this.mockMvc.perform(get("/user?email=cyrille@outlook.fr"))
                .andDo(print())
        // Assert
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Guillet")));
    }

    @Test
    public void addUser() throws Exception {
        // Arrange & Act
        MvcResult mvcResult = this.mockMvc.perform(post("/user/post?email=testuser1@outlook.fr&nom=Test&prenom=user1&password=test10&role=User"))
                .andDo(print()).andReturn();
        // Assert
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        assertEquals(userDAO.getUser("testuser1@outlook.fr").getPrenom(), "user1");

    }

    @Test
    public void updateUser() throws Exception {
        // Arrange
        MockHttpSession session = new MockHttpSession();
        User user = new User();
        user.setEmail("leane@outlook.fr");
        session.setAttribute("user", user);

        // Act
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.put("/user/put/?email=leane@outlook.fr&nom=Guillet&prenom=Leane2&password=test2&role=User")
                .session(session);
        MvcResult result =  this.mockMvc.perform(builder)
                .andDo(print())

                // Assert
                .andExpect(MockMvcResultMatchers.status()
                        .isOk())
                .andReturn();
        assertEquals(userDAO.getUser("leane@outlook.fr").getPrenom(), "Leane2");
    }


    @Test
    public void deleteUser() throws Exception {
        // Arrange
        MockHttpSession session = new MockHttpSession();
        User user = new User();
        user.setEmail("testdelete@outlook.fr");
        session.setAttribute("user", user);

        // Act
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.delete("/user/delete/?email=testdelete@outlook.fr")
                .session(session);
        this.mockMvc.perform(builder)
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status()
                        .isOk());
    }


    @Test
    public void addAccoutRIB() throws Exception {
        // Arrange
        MockHttpSession session = new MockHttpSession();
        User user = new User();
        user.setEmail("cyrille@outlook.fr");
        session.setAttribute("user", user);

        // Act
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/user/createaccount?ribNom=Credit Mutuel RIB 5&ribNumber=FR76 1234 5648 8400 0204 0320 105&userMail=cyrille@outlook.fr&moyenType=RIB")
                .session(session);
        MvcResult result =  this.mockMvc.perform(builder)
                .andDo(print())

                // Assert
                .andExpect(MockMvcResultMatchers.status()
                        .isOk())
                .andReturn();
        assertEquals(ribRepository.getByRibNom("Credit Mutuel RIB 5").getRibNumber(), "FR76 1234 5648 8400 0204 0320 105");

    }

    @Test
    public void addAccoutCB() throws Exception {
        // Arrange
        MockHttpSession session = new MockHttpSession();
        User user = new User();
        user.setEmail("cyrille@outlook.fr");
        session.setAttribute("user", user);

        // Act
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/user/createaccount?cbNom=VISA CYG&cbNumber=12345678&cbValide=123&userMail=cyrille@outlook.fr&moyenType=CB")
                .session(session);
        MvcResult result =  this.mockMvc.perform(builder)
                .andDo(print())

                // Assert
                .andExpect(MockMvcResultMatchers.status()
                        .isOk())
                .andReturn();
        assertEquals(cbRepository.getByCbNom("VISA CYG").getCbNumber(), "12345678");

    }

    @Test
    public void deleteAccoutCB() throws Exception {
        // Arrange
        MockHttpSession session = new MockHttpSession();
        User user = new User();
        user.setEmail("cyrille@outlook.fr");
        session.setAttribute("user", user);

        // Act
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.delete("/user/deletecb/?emailuser=cyrille@outlook.fr&cbnom=VISA CYG TestDel")
                .session(session);
        this.mockMvc.perform(builder)
                .andDo(print())

                // Assert
                .andExpect(MockMvcResultMatchers.status()
                        .isOk());
    }

    @Test
    public void deleteAccoutRIB() throws Exception {
        // Arrange
        MockHttpSession session = new MockHttpSession();
        User user = new User();
        user.setEmail("cyrille@outlook.fr");
        session.setAttribute("user", user);

        // Act
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.delete("/user/deleterib/?emailuser=cyrille@outlook.fr&ribnom=Credit Mutuel RIB Testdel")
                .session(session);
        this.mockMvc.perform(builder)
                .andDo(print())

                // Assert
                .andExpect(MockMvcResultMatchers.status()
                        .isOk());
    }


}
