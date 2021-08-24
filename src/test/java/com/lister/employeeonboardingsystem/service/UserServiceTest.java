package com.lister.employeeonboardingsystem.service;


import com.lister.employeeonboardingsystem.model.Address;
import com.lister.employeeonboardingsystem.model.Demographics;
import com.lister.employeeonboardingsystem.model.User;
import com.lister.employeeonboardingsystem.voobject.LoginInput;
import com.lister.employeeonboardingsystem.repository.UserRepository;
import com.lister.employeeonboardingsystem.voobject.LoginResponseVo;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertEquals;

import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class UserServiceTest {
    @InjectMocks
    UserService userService;
    @Mock
    UserRepository userRepository;
    @Before
    public void init(){
        initMocks(this);
    }
    @Test
    public void saveLoginInfoTest(){
        System.out.println("Testing the function that saves the login information about an employee");
        User user = new User();
        userService.saveUser(user,UUID.randomUUID());
        verify(userRepository,times(1)).save(user);
    }

    @Test
    public void validateTest(){
        UUID uuid = UUID.randomUUID();
        System.out.println("Testing the function that validates the login credentials of an employee");
        LoginInput loginInput = new LoginInput("sharathraju@gmail.com","Sharath@123");
        List<Address> addressSet=new ArrayList<Address>();
        Address address1= new Address(3, "present", "ABC apratnemt", "chinna chowk", "ngo colony", "India", "kadpa", "Ap", "15°N, 30°E.",516002);
        Address address2 = new Address(4, "permanent", "zolo spencer", "katabomma street", "Perungudi", "India", "Chennai", "TamilNadu", "15°N, 30°E.", 600096);
        addressSet.add(address1);
        addressSet.add(address2);
        Demographics demographics =new Demographics(33,"LIS2401",1,"hello", "09182615138", "sharathrajuaddepalli@gmail.com","B+ve", "302233775698", LocalDate.of(1999,9,23), "male", 10.0, 97.7, 8.6, "Raju", "Rajini","Raju", "father","09182615138","Sharath@123", LocalDateTime.of(2000,9,23,8,45,50), null, "null",addressSet);
        User user = new User(1,"sharathraju@gmail.com","Sharath@123","Sharath Raju",1, demographics);
        when(userRepository.findByEmailIdAndPassword("sharathraju@gmail.com","Sharath@123")).thenReturn(user);
        userService.validate(loginInput, uuid);
        verify(userRepository,times(1)).findByEmailIdAndPassword(loginInput.getEmail(),loginInput.getPassword());
    }

    @Test
    public void validateTestCredentails(){
        UUID uuid = UUID.randomUUID();
        System.out.println("Testing the validate function if invalid credentails are provided");
        LoginInput loginInput = new LoginInput("sharathraju@gmail.com","Sharath@123");
        LoginInput invalidlLoginInput = new LoginInput("sharathraju@gmail.com","Sharath@12");

        List<Address> addressSet=new ArrayList<Address>();
        Address address1= new Address(3, "present", "ABC apratnemt", "chinna chowk", "ngo colony", "India", "kadpa", "Ap", "15°N, 30°E.",516002);
        Address address2 = new Address(4, "permanent", "zolo spencer", "katabomma street", "Perungudi", "India", "Chennai", "TamilNadu", "15°N, 30°E.", 600096);
        addressSet.add(address1);
        addressSet.add(address2);
        Demographics demographics =new Demographics(33,"LIS2401",1,"hello", "09182615138", "sharathrajuaddepalli@gmail.com","B+ve", "302233775698", LocalDate.of(1999,9,23), "male", 10.0, 97.7, 8.6, "Raju", "Rajini","Raju", "father","09182615138","Sharath@123", LocalDateTime.of(2000,9,23,8,45,50), null, "null",addressSet);
        User user = new User(1,"sharathraju@gmail.com","Sharath@123","Sharath Raju",1, demographics);
        LoginResponseVo loginResponseVo = new LoginResponseVo("HR",1,"HR");
        when(userService.validate(loginInput,uuid)).thenReturn(loginResponseVo);
        assertEquals("HR", userService.validate(loginInput,uuid).getRoleName());
        assertEquals(1, userService.validate(loginInput,uuid).getUserId());
        assertEquals(0, userService.validate(invalidlLoginInput,uuid).getRoleName());
        assertEquals(0, userService.validate(invalidlLoginInput,uuid).getUserId());

    }

}
