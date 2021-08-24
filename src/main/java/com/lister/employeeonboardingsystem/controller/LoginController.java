package com.lister.employeeonboardingsystem.controller;

import com.fasterxml.uuid.Generators;
import com.lister.employeeonboardingsystem.voobject.LoginInput;
import com.lister.employeeonboardingsystem.voobject.LoginResponseVo;
import com.lister.employeeonboardingsystem.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.UUID;

/**
 *
 */
@RestController
@Slf4j
public class LoginController {

    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    /**
     * @param loginInfo
     * @return
     */
    @CrossOrigin
    @PostMapping("/login")
    public ResponseEntity<LoginResponseVo> doLogin(@Valid @RequestBody LoginInput loginInfo) {
        UUID uuid = Generators.timeBasedGenerator().generate();
        log.info("trying to logging in with credentials"+loginInfo+"with uuid"+uuid.toString());
        return ResponseEntity.ok(userService.validate(loginInfo,uuid));
    }

}
