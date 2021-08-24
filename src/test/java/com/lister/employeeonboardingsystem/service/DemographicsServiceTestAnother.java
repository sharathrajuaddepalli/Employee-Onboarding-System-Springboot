package com.lister.employeeonboardingsystem.service;

import com.lister.employeeonboardingsystem.model.Address;
import com.lister.employeeonboardingsystem.model.Demographics;
import com.lister.employeeonboardingsystem.voobject.StatusUpdateInput;
import com.lister.employeeonboardingsystem.repository.DemographicsRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertEquals;

import static org.mockito.Mockito.*;

public class DemographicsServiceTestAnother {
    @InjectMocks
    DemographicsService demographicsService;

    @Mock
    EmailService emailService;

    @Mock
    DemographicsRepository demographicsRepository;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void saveDemographicsTest(){
        System.out.println("Testing the function that saves the Demographic Info of an employee");
        List<Address> addressSet=new ArrayList<Address>();
        Address address1= new Address(3, "present", "ABC apratnemt", "chinna chowk", "ngo colony", "India", "kadpa", "Ap", "15°N, 30°E.",516002);
        Address address2 = new Address(4, "permanent", "zolo spencer", "katabomma street", "Perungudi", "India", "Chennai", "TamilNadu", "15°N, 30°E.", 600096);
        addressSet.add(address1);
        addressSet.add(address2);
        Demographics demographics =new Demographics(33,"LIS2401",1,"hello", "09182615138", "sharathrajuaddepalli@gmail.com","B+ve", "302233775698", LocalDate.of(1999,9,23), "male", 10.0, 97.7, 8.6, "Raju", "Rajini","Raju", "father","09182615138","Sharath@123", LocalDateTime.of(2000,9,23,8,45,50), null, "null",addressSet);
        demographicsService.saveDemographics(demographics, UUID.randomUUID());
        verify(demographicsRepository,times(1)).save(demographics);
    }

    @Test
    public void updateDemographicsTest(){
        System.out.println("Testing the function that updates the status of the demographic info : save function getting called or not");
        StatusUpdateInput statusUpdateInput = new StatusUpdateInput(33,"approve","");
        UUID uuid = UUID.randomUUID();
        List<Address> addressSet=new ArrayList<Address>();
        Address address1= new Address(3, "present", "ABC apratnemt", "chinna chowk", "ngo colony", "India", "kadpa", "Ap", "15°N, 30°E.",516002);
        Address address2 = new Address(4, "permanent", "zolo spencer", "katabomma street", "Perungudi", "India", "Chennai", "TamilNadu", "15°N, 30°E.", 600096);
        addressSet.add(address1);
        addressSet.add(address2);
        Demographics demographics =new Demographics(33,"LIS2401",1,"hello", "09182615138", "sharathrajuaddepalli@gmail.com","B+ve", "302233775698", LocalDate.of(1999,9,23), "male", 10.0, 97.7, 8.6, "Raju", "Rajini","Raju", "father","09182615138","Sharath@123", LocalDateTime.of(2000,9,23,8,45,50), null, "null",addressSet);
        when(demographicsRepository.findByEmpId(33)).thenReturn(demographics);
        demographicsService.updateStatus(statusUpdateInput,uuid);
        verify(demographicsRepository,times(33)).save(demographics);
    }

    @Test
    public void updateDemographicsTestStatusChangeWhenAccepted(){
        System.out.println("Testing the function that updates the status of the demographic info : status changing to Completed or not");
        StatusUpdateInput statusUpdateInput = new StatusUpdateInput(33,"approve","");
        List<Address> addressSet=new ArrayList<Address>();
        Address address1= new Address(3, "present", "ABC apratnemt", "chinna chowk", "ngo colony", "India", "kadpa", "Ap", "15°N, 30°E.",516002);
        Address address2 = new Address(4, "permanent", "zolo spencer", "katabomma street", "Perungudi", "India", "Chennai", "TamilNadu", "15°N, 30°E.", 600096);
        addressSet.add(address1);
        addressSet.add(address2);
        Demographics demographics =new Demographics(33,"LIS2401",1,"hello", "09182615138", "sharathrajuaddepalli@gmail.com","B+ve", "302233775698", LocalDate.of(1999,9,23), "male", 10.0, 97.7, 8.6, "Raju", "Rajini","Raju", "father","09182615138","Sharath@123", LocalDateTime.of(2000,9,23,8,45,50), null, "null",addressSet);
        when(demographicsRepository.findByEmpId(33)).thenReturn(demographics);
        demographicsService.updateStatus(statusUpdateInput,UUID.randomUUID());
        assertEquals(demographics.getStatus(),"Completed");
    }

    @Test
    public void updateDemographicsTestStatusChangeWhenRejected(){
        UUID uuid = UUID.randomUUID();
        System.out.println("Testing the function that updates the status of the demographic info : status changing to Rejected or not");
        StatusUpdateInput statusUpdateInput = new StatusUpdateInput(33,"reject","mapcoordinates");
        List<Address> addressSet=new ArrayList<Address>();
        Address address1= new Address(3, "present", "ABC apratnemt", "chinna chowk", "ngo colony", "India", "kadpa", "Ap", "15°N, 30°E.",516002);
        Address address2 = new Address(4, "permanent", "zolo spencer", "katabomma street", "Perungudi", "India", "Chennai", "TamilNadu", "15°N, 30°E.", 600096);
        addressSet.add(address1);
        addressSet.add(address2);
        Demographics demographics =new Demographics(33,"LIS2401",1,"hello", "09182615138", "sharathrajuaddepalli@gmail.com","B+ve", "302233775698", LocalDate.of(1999,9,23), "male", 10.0, 97.7, 8.6, "Raju", "Rajini","Raju", "father","09182615138","Sharath@123", LocalDateTime.of(2000,9,23,8,45,50), null, "null",addressSet);
        when(demographicsRepository.findByEmpId(33)).thenReturn(demographics);
        demographicsService.updateStatus(statusUpdateInput,uuid);
        assertEquals(demographics.getStatus(),"Rejected");
        assertEquals("mapcoordinates", demographics.getRejectReason());
        verify(emailService,times(1)).sendMail(demographics.getEmailId(),"Your submitted form was rejected due to following", statusUpdateInput.getRejectReason(), uuid);
    }

}
