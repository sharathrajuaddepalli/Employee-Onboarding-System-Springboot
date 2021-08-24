package com.lister.employeeonboardingsystem.exception;

/**
 *
 */
public class InvalidActionException extends RuntimeException{
    public InvalidActionException(){
        super("Invalid action sent");
    }
}
