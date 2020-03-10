package com.bank.commonservice.service;

import com.bank.commonservice.controller.dto.*;
import com.bank.commonservice.controller.dto.transfer.TransferResponseDTO;
import org.springframework.http.*;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;


@Service
public class AccountService {

    private final CheckAccountParametersService checkAccountParametersService;
    private final GetAccountParametersService getAccountParametersService;
    private final TransferAccountMoneyService transferAccountMoneyService;

    public AccountService(CheckAccountParametersService checkAccountParametersService,
                          GetAccountParametersService getAccountParametersService,
                          TransferAccountMoneyService transferAccountMoneyService) {

        this.checkAccountParametersService = checkAccountParametersService;
        this.getAccountParametersService = getAccountParametersService;
        this.transferAccountMoneyService = transferAccountMoneyService;
    }

    public AccountResponseDTO getAccount(Long customerId){
        CustomerResponseDTO customerResponseDTO = getAccountParametersService.getCustomer(customerId);
        BillResponseDTO[] bills = getAccountParametersService.getBills(customerId);

        return new AccountResponseDTO(customerResponseDTO, bills);
    }

    public TransferResponseDTO transferMoneyFromOneBillToAnotherBill(
            Long customerId, Long firstBillId, Long secondBillId, BigDecimal transaction) throws IOException {

        checkAccountParametersService.checkParametersOneCustomer(customerId, firstBillId, secondBillId, transaction);
        ResponseEntity<String> responsePayment = transferAccountMoneyService.postRequestPayment(firstBillId, transaction);
        ResponseEntity<String> responseAdjustment = transferAccountMoneyService.postRequestAdjustment(secondBillId, transaction);
        BillResponseGetBillDTO paymentToJSON = transferAccountMoneyService.getResponsePayment(responsePayment);
        BillResponseGetBillDTO adjustmentToJSON = transferAccountMoneyService.getResponseAdjustment(responseAdjustment);

        return new TransferResponseDTO(paymentToJSON, adjustmentToJSON);
    }

}
