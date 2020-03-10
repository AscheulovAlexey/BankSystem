package com.bank.commonservice.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AccountResponseDTO {

    @NotNull(message = "Should be initialized")
    private CustomerResponseDTO customerResponseDTO;

    private BillResponseDTO[] billResponseDTO;

}
