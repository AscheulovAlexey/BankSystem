package com.bank.commonservice.exception.handler;

import com.bank.commonservice.exception.CustomerNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class NotFoundCustomerHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<NotFoundExceptionHTTPRequest> handlerCustomerNotFoundException (CustomerNotFoundException ex){
        return new ResponseEntity<>(new NotFoundExceptionHTTPRequest(ex.getMessage(), 404,
                "CustomerNotFoundException"), HttpStatus.NOT_FOUND);
    }

}
