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
public class BillResponseGetBillDTO {

    @NotNull(message = "Should be initialized")
    private Long customerId;

    @NotNull(message = "Should be initialized")
    private BigDecimal balance;

}
