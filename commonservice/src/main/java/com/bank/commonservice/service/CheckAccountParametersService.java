package com.bank.commonservice.service;

import com.bank.commonservice.controller.dto.BillResponseGetBillDTO;
import com.bank.commonservice.exception.BillNotBelongThisCustomerException;
import com.bank.commonservice.exception.NotEnoughMoneyException;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

public class CheckAccountParametersService {

    private final GetAccountParametersService getAccountParametersService;

    @Autowired
    public CheckAccountParametersService(GetAccountParametersService getAccountParametersService) {
        this.getAccountParametersService = getAccountParametersService;
    }

    public void checkParametersOneCustomer(Long customerId, Long firstBillId, Long secondBillId,
                                           BigDecimal transaction){

        BillResponseGetBillDTO firstBill = getAccountParametersService.getBill(firstBillId);
        BillResponseGetBillDTO secondBill = getAccountParametersService.getBill(secondBillId);

        if (firstBill.getBalance().compareTo(transaction) == -1){
            throw new NotEnoughMoneyException("Not enough money on bill with id = " + firstBillId +
                    ". Current balance = " + firstBill.getBalance());
        }

        if (firstBill.getCustomerId() != customerId){
            throw new BillNotBelongThisCustomerException("bill with id = " + firstBillId +
                    " not belong customer with id = " + customerId);
        }

        if (secondBill.getCustomerId() != customerId){
            throw new BillNotBelongThisCustomerException("bill with id = " + firstBillId +
                    " not belong customer with id = " + customerId);
        }
    }

}
