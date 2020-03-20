package com.bank.commonservice.controller.dto.transfer;

import com.bank.commonservice.controller.dto.BillResponseGetBillDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class TransferResponseDTO {

    @NotNull(message = "Should be initialized")
    private BillResponseGetBillDTO afterTransactionFirstBill;

    @NotNull(message = "Should be initialized")
    private BillResponseGetBillDTO afterTransactionSecondBill;
}
