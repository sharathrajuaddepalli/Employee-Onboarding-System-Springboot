package com.lister.employeeonboardingsystem.exception;

/**
 *
 */
public class DetailsNotFoundException extends RuntimeException{
    public DetailsNotFoundException(int id){
        super(String.format("Employee with Id %d not found",id));
    }
}
