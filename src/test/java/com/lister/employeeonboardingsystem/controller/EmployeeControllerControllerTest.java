package com.lister.employeeonboardingsystem.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lister.employeeonboardingsystem.voobject.EmployeeDetailsvo;
import com.lister.employeeonboardingsystem.model.Address;
import com.lister.employeeonboardingsystem.model.Demographics;
import com.lister.employeeonboardingsystem.service.DemographicsService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeControllerControllerTest {

    @InjectMocks
    private EmployeeController employeeController;

    private MockMvc mockMvc;

    @Mock
    private DemographicsService demographicsService;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        employeeController = mock(EmployeeController.class);
    }

    @Test
    public void saveEmployeeDetailsTest() throws Exception{

        mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();
        EmployeeDetailsvo employeeDetailsVo = new EmployeeDetailsvo();
        ObjectMapper objectMapper = new ObjectMapper();
        Assert.assertNotNull(employeeDetailsVo);
        mockMvc.perform(put("/employee/details")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(employeeDetailsVo)))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void addUserWithValidFormatTest() throws Exception{
        System.out.println("Checking with valid format");
        employeeController =mock(EmployeeController.class);
        mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();
        List<Address> addressSet=new ArrayList<Address>();
        Address address1= new Address(3, "present", "ABC apratnemt", "chinna chowk", "ngo colony", "India", "kadpa", "Ap", "15°N, 30°E.",516002);
        Address address2 = new Address(4, "permanent", "zolo spencer", "katabomma street", "Perungudi", "India", "Chennai", "TamilNadu", "15°N, 30°E.", 600096);
        addressSet.add(address1);
        addressSet.add(address2);
        Demographics demographics =new Demographics(33,"LIS2401",1,"hello", "09182615138", "sharathrajuaddepalli@gmail.com","B+ve", "302233775698", LocalDate.of(1999,9,23), "male", 10.0, 97.7, 8.6, "Raju", "Rajini","Raju", "father","09182615138","Sharath@123", LocalDateTime.of(2000,9,23,8,45,50), null, "null",addressSet);
        EmployeeDetailsvo employeeDetailsVo = new EmployeeDetailsvo(1,"SharathRaju","09182615138","B+ve","302233775698", LocalDate.of(1999,9,23),"male",10.0,97.7,8.6,"Raju","Rajit","Raju","father","09182615138",addressSet,"save");
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
        Assert.assertNotNull(employeeDetailsVo);
        mockMvc.perform(put("/employee/details")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(employeeDetailsVo)))
                .andExpect(status().isOk());
    }

}
