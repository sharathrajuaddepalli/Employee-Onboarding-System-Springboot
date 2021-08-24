package com.lister.employeeonboardingsystem.exception;

import com.lister.employeeonboardingsystem.voobject.StatusUpdateInput;
import lombok.extern.slf4j.Slf4j;

/**
 *
 */
@Slf4j
public class GreytbStoreException extends RuntimeException{
    public GreytbStoreException(StatusUpdateInput input){
        super("failed to store record");
        log.error("failed to save details for object {}",input);
    }
}
