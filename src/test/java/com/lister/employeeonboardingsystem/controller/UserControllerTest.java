package com.lister.employeeonboardingsystem.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lister.employeeonboardingsystem.voobject.LoginInput;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {
    @InjectMocks
    private LoginController loginController;

    private MockMvc mockMvc;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
    }
    @Test
    public void findValidUserTest() throws Exception{
        loginController = mock(LoginController.class);
        mockMvc = MockMvcBuilders.standaloneSetup(loginController).build();
        LoginInput requestBody = new LoginInput();
        requestBody.setEmail(null);
        requestBody.setPassword("sharath@1");
        ObjectMapper objectMapper = new ObjectMapper();
        Assert.assertNotNull(requestBody);
        mockMvc.perform(post("/login")
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .content(objectMapper.writeValueAsString(requestBody)))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void findValidUserWithValidFormatTest() throws Exception{
        System.out.println("Checking with valid format");
        loginController =mock(LoginController.class);
        mockMvc = MockMvcBuilders.standaloneSetup(loginController).build();
        LoginInput requestBody = new LoginInput();
        requestBody.setEmail("Aabc@gmail.com");
        requestBody.setPassword("Sharath@1");
        ObjectMapper objectMapper = new ObjectMapper();
        Assert.assertNotNull(requestBody);
        mockMvc.perform(post("/login")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(requestBody)))
                .andExpect(status().isOk());

    }
}
