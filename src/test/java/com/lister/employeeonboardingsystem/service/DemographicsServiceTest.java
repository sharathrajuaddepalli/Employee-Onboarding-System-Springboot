package com.lister.employeeonboardingsystem.service;

import com.lister.employeeonboardingsystem.controller.HrController;
import com.lister.employeeonboardingsystem.model.Address;
import com.lister.employeeonboardingsystem.model.Demographics;
import com.lister.employeeonboardingsystem.voobject.StatusUpdateInput;
import com.lister.employeeonboardingsystem.repository.DemographicsRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.junit.Assert.assertEquals;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemographicsServiceTest {
    @InjectMocks
    DemographicsService demographicsService;

    @Mock
    DemographicsRepository demographicsRepository;

    @Before
    public void init(){
        initMocks(this);
    }

    @Test
    public void getEmployessTest() {
        System.out.println("Testing the function that retreives all employees");
        List<Address> addressSet = new ArrayList<Address>();
        Address address1 = new Address(3, "present", "ABC apratnemt", "chinna chowk", "ngo colony", "India", "kadpa", "Ap", "15°N, 30°E.", 516002);
        Address address2 = new Address(4, "permanent", "zolo spencer", "katabomma street", "Perungudi", "India", "Chennai", "TamilNadu", "15°N, 30°E.", 600096);
        addressSet.add(address1);
        addressSet.add(address2);
        when(demographicsService.getEmployees()).thenReturn(Stream.of(new Demographics(1, "LIS2400", 1, "SharathRaju", "09182615139", "sharathrajaddepalli@gmail.com", "B+ve", "302233775697", null, "male", 10.0, 97.7, 8.6, "Raju", "Rajini", "Raju", "father", "09182615137", "Sharath@123", null, null, "null", addressSet),
                new Demographics(2, "LIS2401", 1, "hello", "09182615138", "sharathrajuaddepalli@gmail.com", "B+ve", "302233775698", LocalDate.of(1999, 9, 23), "male", 10.0, 97.7, 8.6, "Raju", "Rajini", "Raju", "father", "09182615138", "Sharath@123", LocalDateTime.of(2000, 9, 23, 8, 45, 50), null, "null", addressSet)).collect(Collectors.toList()));
        assertEquals(2, demographicsService.getEmployees().size());
    }

    @Test
    public void getEmployeeByEmailTest() {
        System.out.println("Testing the function that retrieves demographic info with mail id");
        List<Address> addressSet = new ArrayList<Address>();
        Address address1 = new Address(3, "present", "ABC apratnemt", "chinna chowk", "ngo colony", "India", "kadpa", "Ap", "15°N, 30°E.", 516002);
        Address address2 = new Address(4, "permanent", "zolo spencer", "katabomma street", "Perungudi", "India", "Chennai", "TamilNadu", "15°N, 30°E.", 600096);
        addressSet.add(address1);
        addressSet.add(address2);
        String email = "sharathrajuadde@gmail.com";
        when(demographicsRepository.findByEmailId(email)).thenReturn(new Demographics(2, "LIS2401", 1, "hello", "09182615138", "sharathrajuadde@gmail.com", "B+ve", "302233775698", LocalDate.of(1999, 9, 23), "male", 10.0, 97.7, 8.6, "Raju", "Rajini", "Raju", "father", "09182615138", "Sharath@123", LocalDateTime.of(2000, 9, 23, 8, 45, 50), null, "null", addressSet));
        assertEquals(demographicsRepository.findByEmailId(email).getEmailId(), email);
    }

    @Test
    public void getEmployeeByEmpIdTest() {
        System.out.println("Testing the function which retrieves the demographic info with employee id");
        List<Address> addressSet = new ArrayList<Address>();
        Address address1 = new Address(3, "present", "ABC apratnemt", "chinna chowk", "ngo colony", "India", "kadpa", "Ap", "15°N, 30°E.", 516002);
        Address address2 = new Address(4, "permanent", "zolo spencer", "katabomma street", "Perungudi", "India", "Chennai", "TamilNadu", "15°N, 30°E.", 600096);
        addressSet.add(address1);
        addressSet.add(address2);
        Demographics demographics = new Demographics(33, "LIS2401", 1, "hello", "09182615138", "sharathrajuaddepalli@gmail.com", "B+ve", "302233775698", LocalDate.of(1999, 9, 23), "male", 10.0, 97.7, 8.6, "Raju", "Rajini", "Raju", "father", "09182615138", "Sharath@123", LocalDateTime.of(2000, 9, 23, 8, 45, 50), null, "null", addressSet);
        //when(demographicsService.getDemographicInfoByEmpId(33)).thenReturn(demographics);
        //assertEquals(demographics, demographicsService.getDemographicInfoByEmpId(33));
    }


    @Test
    public void codeGeneratorTest() {
        System.out.println("Testing the function that generates the employee code");
        when(demographicsRepository.findMaxCode()).thenReturn("LIS2400");
        String testCode = "LIS2400";
        assertEquals(testCode,demographicsService.generateCode());
    }

    @Test
    public void codeGeneratorWhenNullTest() {
        System.out.println("Testing the code generator when returned value is null");
        when(demographicsRepository.findMaxCode()).thenReturn(null);
        assertEquals("LIS2400", demographicsService.generateCode());
    }

    @Test
    public void updateDemographicInfoTest() {

        System.out.println("Testing the function that updates the status of the demographic info according to the test");
        StatusUpdateInput statusUpdateInput = new StatusUpdateInput(33, "accept", "");
        DemographicsService demographicsServiceMock = mock(DemographicsService.class);
        List<Address> addressSet = new ArrayList<Address>();
        Address address1 = new Address(3, "present", "ABC apratnemt", "chinna chowk", "ngo colony", "India", "kadpa", "Ap", "15°N, 30°E.", 516002);
        Address address2 = new Address(4, "permanent", "zolo spencer", "katabomma street", "Perungudi", "India", "Chennai", "TamilNadu", "15°N, 30°E.", 600096);
        addressSet.add(address1);
        addressSet.add(address2);
        UUID uuid = UUID.randomUUID();
        when(demographicsServiceMock.getEmployeeDetails(33,uuid)).thenReturn(new Demographics(33, "LIS2401", 1, "hello", "09182615138", "sharathrajuaddepalli@gmail.com", "B+ve", "302233775698", LocalDate.of(1999, 9, 23), "male", 10.0, 97.7, 8.6, "Raju", "Rajini", "Raju", "father", "09182615138", "Sharath@123", LocalDateTime.of(2000, 9, 23, 8, 45, 50), "Completed", "null", addressSet));
        demographicsServiceMock.updateStatus(statusUpdateInput,uuid);
        Demographics demographics = demographicsServiceMock.getEmployeeDetails(33,uuid);
        assertEquals(demographics.getStatus(), "Completed");

    }
}
