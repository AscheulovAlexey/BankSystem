package com.bank.commonservice.exception.handler;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class NotFoundExceptionHTTPRequest {

    private String message;

    private Integer httpCode;

    private String exception;
}
