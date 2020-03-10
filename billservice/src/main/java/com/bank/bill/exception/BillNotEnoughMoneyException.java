package com.bank.bill.exception;

public class BillNotEnoughMoneyException extends RuntimeException {

    public BillNotEnoughMoneyException(String message) {
        super(message);
    }
}
