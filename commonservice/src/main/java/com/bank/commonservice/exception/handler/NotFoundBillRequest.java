package com.bank.commonservice.exception.handler;

import com.bank.commonservice.exception.BillNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class NotFoundBillRequest extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BillNotFoundException.class)
    public ResponseEntity<NotFoundExceptionHTTPRequest> handlerBillNotFoundException (BillNotFoundException ex){
        return new ResponseEntity<>(new NotFoundExceptionHTTPRequest(ex.getMessage(), 404,
                "NotFoundBillException"), HttpStatus.NOT_FOUND);
    }

}