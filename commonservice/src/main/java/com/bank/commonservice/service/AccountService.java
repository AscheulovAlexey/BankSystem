package com.bank.commonservice.service;

import com.bank.commonservice.controller.dto.*;
import com.bank.commonservice.controller.dto.transfer.TransferResponseDTO;
import org.springframework.http.*;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;


@Service
public class AccountService {

    private final AccountParametersService accountParametersService;
    private final AccountMoneyService accountMoneyService;

    public AccountService(AccountParametersService accountParametersService,
                          AccountMoneyService accountMoneyService) {
        this.accountParametersService = accountParametersService;
        this.accountMoneyService = accountMoneyService;
    }

    public AccountResponseDTO getAccount(Long customerId) {
        CustomerResponseDTO customerResponseDTO = accountParametersService.getCustomer(customerId);
        List<BillResponseDTO> bills = accountParametersService.getBills(customerId);

        return new AccountResponseDTO(customerResponseDTO, bills);
    }

    public TransferResponseDTO transferMoneyFromOneBillToAnotherBill(
            Long customerId, Long firstBillId, Long secondBillId, BigDecimal transaction) throws IOException {

        accountParametersService.checkParametersOneCustomer(customerId, firstBillId, transaction);
        accountParametersService.checkParametersOneCustomer(customerId, secondBillId, transaction);
        ResponseEntity<String> responsePayment = accountMoneyService.postRequestPayment(firstBillId, transaction);
        ResponseEntity<String> responseAdjustment = accountMoneyService.postRequestAdjustment(secondBillId, transaction);
        BillResponseGetBillDTO paymentToJSON = accountMoneyService.getResponsePayment(responsePayment);
        BillResponseGetBillDTO adjustmentToJSON = accountMoneyService.getResponseAdjustment(responseAdjustment);

        return new TransferResponseDTO(paymentToJSON, adjustmentToJSON);
    }

    public TransferResponseDTO transferMoneyFromOneCustomerToAnotherCustomer(
            Long customerFirstId, Long firstBillId,
            Long customerSecondId, Long secondBillId,
            BigDecimal transaction) throws IOException {

        accountParametersService.checkParametersOneCustomer(customerFirstId, firstBillId, transaction);
        accountParametersService.checkParametersOneCustomer(customerSecondId, secondBillId, transaction);
        ResponseEntity<String> responsePayment = accountMoneyService.postRequestPayment(firstBillId, transaction);
        ResponseEntity<String> responseAdjustment = accountMoneyService.postRequestAdjustment(secondBillId, transaction);
        BillResponseGetBillDTO paymentToJSON = accountMoneyService.getResponsePayment(responsePayment);
        BillResponseGetBillDTO adjustmentToJSON = accountMoneyService.getResponseAdjustment(responseAdjustment);

        return new TransferResponseDTO(paymentToJSON, adjustmentToJSON);
    }

}
