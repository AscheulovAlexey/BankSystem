package com.bank.bill.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BillResponseGetByCustomerDTO {

    @NotNull(message = "Should be initialized")
    private Long id;

    @NotNull(message = "Should be initialized")
    private BigDecimal balance;

}
