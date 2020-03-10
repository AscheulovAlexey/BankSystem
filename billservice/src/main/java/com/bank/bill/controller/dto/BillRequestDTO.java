package com.bank.bill.controller.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@NoArgsConstructor
public class BillRequestDTO {

    private Long customerId;

    private BigDecimal balance;

    public BillRequestDTO(Long customerId, BigDecimal balance) {
        this.customerId = customerId;
        this.balance = balance;
    }
}
