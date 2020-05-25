package it.com.example.PayMyBuddy.controllers;

import com.example.PayMyBuddy.PayMyBuddyApplication;
import com.example.PayMyBuddy.dao.AmisDAO;
import com.example.PayMyBuddy.repository.AmisRepository;
import com.example.PayMyBuddy.modeles.Amis;
import com.example.PayMyBuddy.modeles.User;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;


@SpringBootTest(classes = PayMyBuddyApplication.class)
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Sql({"/delete.sql", "/data_test.sql"})
public class controllerAmisTests {



    @Autowired
    MockMvc mockMvc;
    @Autowired
    AmisRepository amisRepository;
    @Autowired
    AmisDAO amisDAO;



    @Test
    public void addAmis() throws Exception {
        MockHttpSession session = new MockHttpSession();

        User user = new User();
        user.setEmail("cyrille@outlook.fr");
        session.setAttribute("user", user);
        Amis listAmis = new Amis();

        listAmis.setAmisUser(2);
        listAmis.setAmisAmis(6);

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/amis/post?emailuser=cyrille@outlook.fr&emailamis=testamis2@outlook.fr")
                .session(session);
        this.mockMvc.perform(builder)
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status()
                        .isOk());

        assertTrue(amisDAO.checkAmisList(listAmis));

    }


    @Test
    public void deleteAmis() throws Exception {
        // Arrange & Act
        MockHttpSession session = new MockHttpSession();
        User user = new User();
        user.setEmail("cyrille@outlook.fr");
        session.setAttribute("user", user);

        Amis listAmis = new Amis();

        listAmis.setAmisUser(2);
        listAmis.setAmisAmis(5);

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.delete("/amis/delete/?emailuser=cyrille@outlook.fr&emailamis=testamis1@outlook.fr")
                .session(session);

        // Assert
        this.mockMvc.perform(builder)
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status()
                        .isOk());

        assertFalse(amisDAO.checkAmisList(listAmis));

    }


}
