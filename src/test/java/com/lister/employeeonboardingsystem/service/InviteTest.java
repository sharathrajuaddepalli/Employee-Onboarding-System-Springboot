package com.lister.employeeonboardingsystem.service;

import com.lister.employeeonboardingsystem.model.Demographics;
import com.lister.employeeonboardingsystem.voobject.InviteInput;
import com.lister.employeeonboardingsystem.model.User;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.UUID;

import static org.mockito.Mockito.*;

public class InviteTest {
    @InjectMocks
    Invite invite;
    @Mock
    UserService userService;
    @Mock
    DemographicsService demographicsService;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
    }
    @Test
    public void saveInfoTest(){
        System.out.println("Testing the save function of Hr Side Post Service");
        InviteInput inviteInput =new InviteInput("ab","bc",1,"sharath");
        when(demographicsService.generateCode()).thenReturn("LIS2400");
         invite.saveInfo(inviteInput, UUID.randomUUID());
          verify(demographicsService,times(1)).generateCode();
       verify(demographicsService,times(1)).saveDemographics(any(Demographics.class),UUID.randomUUID());
        verify(userService,times(1)).saveUser(any(User.class),UUID.randomUUID());
    }
}
