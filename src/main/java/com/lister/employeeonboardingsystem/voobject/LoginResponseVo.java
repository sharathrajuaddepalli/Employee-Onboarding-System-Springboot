package com.lister.employeeonboardingsystem.voobject;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponseVo {
    private String roleName;
    private int userId;
    private String userType;
}
