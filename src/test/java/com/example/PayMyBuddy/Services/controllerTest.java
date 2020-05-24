package com.example.PayMyBuddy.Services;

import com.example.PayMyBuddy.PayMyBuddyApplication;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = PayMyBuddyApplication.class)
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
public class controllerTest {

    @Autowired
    MockMvc mockMvc;


    @Test
    public void httpTest() throws Exception {
        // Arrange & Act
        this.mockMvc.perform(get("/login?email=cyrille@outlook.fr&pass=rsv1000r"))
                .andDo(print())
        // Assert
                .andExpect(status().isOk())
                .andReturn();
    }


}
