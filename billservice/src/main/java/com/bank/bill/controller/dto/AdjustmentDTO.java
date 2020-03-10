package com.bank.bill.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import java.math.BigDecimal;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AdjustmentDTO {

    @Min(value = 0, message = "Should be more than 0")
    private BigDecimal adjustment;
}
