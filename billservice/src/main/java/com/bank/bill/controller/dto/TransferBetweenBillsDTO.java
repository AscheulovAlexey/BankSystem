package com.bank.bill.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TransferBetweenBillsDTO {

    private Long id;

    private Long customerId;

    private BigDecimal balance;
}
