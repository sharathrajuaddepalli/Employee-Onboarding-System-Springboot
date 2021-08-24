package com.lister.employeeonboardingsystem.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lister.employeeonboardingsystem.voobject.InviteInput;
import com.lister.employeeonboardingsystem.voobject.StatusUpdateInput;
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

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HrControllerTest {
    @InjectMocks
    private HrController hrController;

    private MockMvc mockMvc;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        hrController = mock(HrController.class);
    }

    @Test
    public void addUserTest() throws Exception {

        mockMvc = MockMvcBuilders.standaloneSetup(hrController).build();
        InviteInput requestBody = new InviteInput("abc", "abc", 1, "sha");
        ObjectMapper objectMapper = new ObjectMapper();
        Assert.assertNotNull(requestBody);
        mockMvc.perform(post("/hr/employee")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(requestBody)))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void addUserWithValidFormatTest() throws Exception {
        System.out.println("Checking with valid format");
        hrController = mock(HrController.class);
        mockMvc = MockMvcBuilders.standaloneSetup(hrController).build();
        InviteInput requestBody = new InviteInput("Sha@gmail.com", "Sharath@123", 1, "Sharath");
        ObjectMapper objectMapper = new ObjectMapper();
        Assert.assertNotNull(requestBody);
        mockMvc.perform(post("/hr/employee")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(requestBody)))
                .andExpect(status().isOk());
        hrController.addLoginInfo(requestBody);

    }

    @Test
    public void updateEmployeeStatusTest() throws Exception {
        hrController = mock(HrController.class);
        mockMvc = MockMvcBuilders.standaloneSetup(hrController).build();
        StatusUpdateInput requestBody = new StatusUpdateInput();
        requestBody.setEmpId(0);
        requestBody.setAction("accept");
        ObjectMapper objectMapper = new ObjectMapper();
        Assert.assertNotNull(requestBody);
        mockMvc.perform(put("/hr/action")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(requestBody)))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void updateEmployeeStatusWithValidFormatTest() throws Exception {
        System.out.println("Checking with valid format");
        hrController = mock(HrController.class);
        mockMvc = MockMvcBuilders.standaloneSetup(hrController).build();
        StatusUpdateInput requestBody = new StatusUpdateInput();
        requestBody.setEmpId(1);
        requestBody.setAction("accept");
        ObjectMapper objectMapper = new ObjectMapper();
        Assert.assertNotNull(requestBody);
        mockMvc.perform(put("/hr/action")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(requestBody)))
                .andExpect(status().isOk());
        hrController.updateStatus(requestBody);
    }

}
