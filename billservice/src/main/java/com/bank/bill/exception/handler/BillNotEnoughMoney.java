package com.bank.bill.exception.handler;

import com.bank.bill.exception.BillNotEnoughMoneyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class BillNotEnoughMoney extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BillNotEnoughMoneyException.class)
    public ResponseEntity<BillNotFoundExceptionHandlerTemplate> handlerBillNotMoney (BillNotEnoughMoneyException ex){
        return new ResponseEntity<>(new BillNotFoundExceptionHandlerTemplate(
                ex.getMessage(), 500, "BillNotEnoughMoneyException"),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
