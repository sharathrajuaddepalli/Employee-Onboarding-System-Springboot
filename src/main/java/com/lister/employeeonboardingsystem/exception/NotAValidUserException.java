package com.lister.employeeonboardingsystem.exception;

import com.lister.employeeonboardingsystem.voobject.LoginInput;

/**
 *
 */
public class NotAValidUserException extends RuntimeException{
    public NotAValidUserException(LoginInput input){
        super("These are not valid credentials"+"Username : "+input.getEmail()+"  with password: "+input.getPassword());
    }
}
