package com.bank.commonservice.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerResponseDTO {

        @NotNull(message = "Should be initialized")
        private Long id;

        @NotNull(message = "Should be initialized")
        private String name;

        @NotNull(message = "Should be initialized")
        private String phone;

        @NotNull(message = "Should be initialized")
        private String mail;

}
