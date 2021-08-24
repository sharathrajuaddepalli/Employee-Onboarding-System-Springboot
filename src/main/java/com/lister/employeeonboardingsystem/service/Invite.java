package com.lister.employeeonboardingsystem.service;

import com.lister.employeeonboardingsystem.enums.StatusDescription;
import com.lister.employeeonboardingsystem.model.Demographics;
import com.lister.employeeonboardingsystem.voobject.InviteInput;
import com.lister.employeeonboardingsystem.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Slf4j
public class Invite {
    private final DemographicsService demographicsService;
    private final UserService userService;


    public Invite(DemographicsService demographicsService, UserService userService) {
        this.demographicsService = demographicsService;
        this.userService = userService;
    }

    /**
     * @param inviteInput
     * @param uuid
     * @return
     */
    //method that gets called when hr invites the employee
    @Transactional(rollbackFor = Exception.class)
    public boolean saveInfo(InviteInput inviteInput, UUID uuid) {
        log.info("tried to add new user with email id" + inviteInput.getEmail()+"with uuid"+uuid.toString());
        Demographics demographics = new Demographics();
        demographics.setEmailId(inviteInput.getEmail());
        demographics.setName(inviteInput.getName());
        demographics.setPassword(inviteInput.getPassword());
        demographics.setRoleId(inviteInput.getRole());
        demographics.setCode(demographicsService.generateCode());
        demographics.setStatus(StatusDescription.Status.SAVE.getStatusName());
        demographics = demographicsService.saveDemographics(demographics,uuid);
        User user = new User();
        user.setEmailId(inviteInput.getEmail());
        user.setPassword(inviteInput.getPassword());
        user.setName(inviteInput.getName());
        user.setRoleId(inviteInput.getRole());
        user.setDemographics(demographics);
        userService.saveUser(user,uuid);
        return true;
    }
}
