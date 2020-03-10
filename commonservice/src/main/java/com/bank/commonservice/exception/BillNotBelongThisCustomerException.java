package com.bank.commonservice.exception;

public class BillNotBelongThisCustomerException extends RuntimeException {

    public BillNotBelongThisCustomerException(String message){
        super(message);
    }
}
