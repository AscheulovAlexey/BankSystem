package com.bank.bill.exception.handler;

import com.bank.bill.exception.BillNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class BillNotFoundHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BillNotFoundException.class)
    public ResponseEntity<BillNotFoundExceptionHandlerTemplate> handlerBillNotFound (BillNotFoundException ex){
        return new ResponseEntity<>(new BillNotFoundExceptionHandlerTemplate(
                ex.getMessage(), 404, "BillNotFoundException"), HttpStatus.NOT_FOUND);
    }

}
