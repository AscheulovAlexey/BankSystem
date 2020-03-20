package com.bank.commonservice.controller;

import com.bank.commonservice.controller.dto.AccountResponseDTO;
import com.bank.commonservice.controller.dto.transfer.TransferOneCustomerRequestDTO;
import com.bank.commonservice.controller.dto.transfer.TransferResponseDTO;
import com.bank.commonservice.controller.dto.transfer.TransferTwoCustomerRequestDTO;
import com.bank.commonservice.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;

@RestController
public class AccountController {

    private AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/account/{customerId}")
    public AccountResponseDTO getAccount(@PathVariable Long customerId) {
        return accountService.getAccount(customerId);
    }

    @PutMapping("/account/transfer-one-customer")
    public TransferResponseDTO transferForOneCustomer(
            @RequestBody @Valid TransferOneCustomerRequestDTO payment) throws IOException {

        return accountService.transferMoneyFromOneBillToAnotherBill(
                payment.getCustomerId(), payment.getFirstBillId(), payment.getSecondBillId(), payment.getTransaction());
    }

    @PutMapping("/account/transfer-two-customer")
    public TransferResponseDTO transferForTwoCustomer(
            @RequestBody @Valid TransferTwoCustomerRequestDTO payment) throws IOException {

        return accountService.transferMoneyFromOneCustomerToAnotherCustomer(
                payment.getCustomerFirstId(), payment.getFirstBillId(),
                payment.getCustomerSecondId(), payment.getSecondBillId(),
                payment.getTransaction());
    }
}
