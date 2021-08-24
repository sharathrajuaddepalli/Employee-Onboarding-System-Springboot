package com.lister.employeeonboardingsystem.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    String t = "timestamp";
    String m = "message";

    /**
     * @param ex
     * @param request
     * @return
     */
    @ExceptionHandler(value = DetailsNotFoundException.class)
    public ResponseEntity<Object> handleDetailsNotFoundException(
            DetailsNotFoundException ex,WebRequest request
    ){
        log.error("Details for the particular employee was not found"+ex.getMessage());
        Map<String,Object> body = new LinkedHashMap<>();
        body.put(t,LocalDateTime.now());
        body.put(m,"Details not exist in the database Please contact admin");
        return new ResponseEntity<>(body,HttpStatus.NOT_FOUND);
    }

    /**
     * @param ex
     * @return
     */
    @ExceptionHandler(value=NotAValidUserException.class)
    public ResponseEntity<Object> handleNotAValidUserException(NotAValidUserException ex){
        log.error(ex.getMessage());
        Map<String,Object> body = new LinkedHashMap<>();
        body.put(t,LocalDateTime.now());
        body.put(m,"Invalid credentials");
        return new ResponseEntity<>(body,HttpStatus.NOT_FOUND);
    }

    /**
     * @param exception
     * @return
     */
    @ExceptionHandler(value=InvalidActionException.class)
    public ResponseEntity<Object> handleInvalidActionException(InvalidActionException exception){
        log.error("error occured when invalid action is sent {}", exception.getMessage());
        Map<String,Object> body = new LinkedHashMap<>();
        body.put(t,LocalDateTime.now());
        body.put(m,"Action you are trying to send is not correct please check again");
        return new ResponseEntity<>(body,HttpStatus.BAD_REQUEST);
    }

    /**
     * @param ex
     * @return
     */
    @ExceptionHandler(value=Exception.class)
    public ResponseEntity<Object> handleException(Exception ex){
        log.error("error occured not custom"+ex.getMessage());
        Map<String,Object> body = new LinkedHashMap<>();
        body.put(t,LocalDateTime.now());
        body.put(m,"Seems like Server is busy right now, Please try again after some time");
        return new ResponseEntity<>(body,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * @param exception
     * @return
     */
    @ExceptionHandler(value=GreytbStoreException.class)
    public ResponseEntity<Object> hanldeGreytDbStoreException(GreytbStoreException exception){
        Map<String,Object> body = new LinkedHashMap<>();
        body.put(t,LocalDateTime.now());
        body.put(m,"Seems like Server is busy right now, Please try again after some time");
        return new ResponseEntity<>(body,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * @param ex
     * @param headers
     * @param status
     * @param request
     * @return
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.error(ex.getMessage());
        Map<String,Object> body = new LinkedHashMap<>();
        body.put(t, LocalDateTime.now());
        body.put("status",status.value());

        List<String> errors = ex.getBindingResult()
                                    .getFieldErrors()
                                    .stream()
                                    .map(x->x.getDefaultMessage())
                                    .collect(Collectors.toList());
        body.put("errors",errors);
        return new ResponseEntity<>(body,HttpStatus.BAD_REQUEST);
    }
}
