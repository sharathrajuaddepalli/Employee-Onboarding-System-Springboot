package com.lister.employeeonboardingsystem.service;

import com.lister.employeeonboardingsystem.enums.StatusDescription;
import com.lister.employeeonboardingsystem.exception.NotAValidUserException;
import com.lister.employeeonboardingsystem.model.User;
import com.lister.employeeonboardingsystem.voobject.LoginInput;
import com.lister.employeeonboardingsystem.voobject.LoginResponseVo;
import com.lister.employeeonboardingsystem.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final RoleService roleService;

    public UserService(UserRepository userRepository, RoleService roleService) {
        this.userRepository = userRepository;
        this.roleService = roleService;
    }

    /**
     * @param user
     * @param uuid
     */
    public void saveUser(User user,UUID uuid) {
        log.info("saving the user with uuid"+uuid.toString());
        userRepository.save(user);
    }

    /**
     * @param input
     * @param uuid
     * @return
     */
    //method that validates the user with the submitted credentials and existing details
    public LoginResponseVo validate(LoginInput input, UUID uuid) {
        log.info("tried to login with email" + input.getEmail()+"with uuid"+uuid.toString());
        User validCredentials = userRepository.findByEmailIdAndPassword(input.getEmail(), input.getPassword());
        LoginResponseVo r = new LoginResponseVo();
        if ((validCredentials != null)) {
            r.setRoleName(roleService.findRoleNameById(validCredentials.getRoleId()));
            r.setUserId(validCredentials.getDemographics().getEmpId());
            if (validCredentials.getDemographics().getAadharNumber() != null)
                r.setUserType(StatusDescription.Status.UPDATED.getStatusName());
            else
                r.setUserType(StatusDescription.Status.NEW.getStatusName());
        } else
            throw new NotAValidUserException(input);
        return r;
    }
}
