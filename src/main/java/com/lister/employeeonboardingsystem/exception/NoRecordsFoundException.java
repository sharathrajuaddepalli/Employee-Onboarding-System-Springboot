package com.lister.employeeonboardingsystem.exception;

/**
 *
 */
public class NoRecordsFoundException extends RuntimeException{
    public NoRecordsFoundException(){
        super("No Records found");
    }
}
