package com.bank.bill.exception.handler;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BillNotFoundExceptionHandlerTemplate {

    private String message;

    private Integer httpCode;

    private String exception;

}
