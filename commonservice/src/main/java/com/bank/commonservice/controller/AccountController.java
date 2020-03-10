package com.bank.commonservice.controller;

import com.bank.commonservice.controller.dto.AccountResponseDTO;
import com.bank.commonservice.controller.dto.transfer.TransferRequestDTO;
import com.bank.commonservice.controller.dto.transfer.TransferResponseDTO;
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

    @PutMapping("/account/payment")
    public TransferResponseDTO getAccount(@RequestBody @Valid TransferRequestDTO payment) throws IOException {

        return accountService.transferMoneyFromOneBillToAnotherBill(
                payment.getCustomerId(), payment.getFirstBillId(), payment.getSecondBillId(), payment.getTransaction());
    }
}
