package com.bank.customerservice.exception.handler;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CustomerNotFoundExceptionTemplate {

    private String message;

    private Integer httpCode;

    private String exception;

}