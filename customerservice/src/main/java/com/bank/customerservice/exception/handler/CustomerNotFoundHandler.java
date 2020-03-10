package com.bank.customerservice.exception.handler;

import com.bank.customerservice.exception.CustomerNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomerNotFoundHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<CustomerNotFoundExceptionTemplate> handlerCustomerNotFound (CustomerNotFoundException ex){
        return new ResponseEntity<>(new CustomerNotFoundExceptionTemplate(ex.getMessage(), 404,
                "CustomerNotFoundException"), HttpStatus.NOT_FOUND);
    }

}
