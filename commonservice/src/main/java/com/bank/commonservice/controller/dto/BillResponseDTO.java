package com.bank.commonservice.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BillResponseDTO {

    @NotNull(message = "Should be initialized")
    private Long id;

    @NotNull(message = "Should be initialized")
    private BigDecimal balance;

}
