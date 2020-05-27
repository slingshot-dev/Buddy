package it.com.example.PayMyBuddy.controllers;


import com.example.PayMyBuddy.PayMyBuddyApplication;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import java.util.ResourceBundle;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = PayMyBuddyApplication.class)
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
public class controllerLoginTests {





    @Autowired
    MockMvc mockMvc;

    ResourceBundle bundle = ResourceBundle.getBundle("TestResources");

    @Test
    public void getLoginOKTest() throws Exception {
        // Arrange & Act
        MvcResult result = this.mockMvc.perform(get("/login").contentType(MediaType.APPLICATION_JSON).content(bundle.getString("login")))
                .andDo(print())
        // Assert
                .andExpect(status().isOk())
                .andReturn();
        assertEquals(result.getResponse().getContentAsString(), "true");
    }

    @Test
    public void getLoginKOTest() throws Exception {

        MvcResult result = this.mockMvc.perform(get("/login").contentType(MediaType.APPLICATION_JSON).content(bundle.getString("loginko")))
                .andDo(print())
                .andReturn();

        assertEquals(result.getResponse().getContentAsString(), "false");
    }
}
