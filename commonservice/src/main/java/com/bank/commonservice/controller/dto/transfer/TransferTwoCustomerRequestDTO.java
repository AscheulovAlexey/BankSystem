package com.bank.commonservice.controller.dto.transfer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class TransferTwoCustomerRequestDTO {

    @NotNull(message = "Should be initialized")
    private Long customerFirstId;

    @NotNull(message = "Should be initialized")
    private Long firstBillId;

    @NotNull(message = "Should be initialized")
    private Long customerSecondId;

    @NotNull(message = "Should be initialized")
    private Long secondBillId;

    @Min(value = 0, message = "Should be more than 0")
    private BigDecimal transaction;

}
