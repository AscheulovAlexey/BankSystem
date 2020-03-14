package com.bank.bill.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TransferBetweenBillsDTO {

    @NotNull(message = "Should be initialized")
    private Long id;

    @NotNull(message = "Should be initialized")
    private Long customerId;

    @NotNull(message = "Should be initialized")
    private BigDecimal balance;
}
