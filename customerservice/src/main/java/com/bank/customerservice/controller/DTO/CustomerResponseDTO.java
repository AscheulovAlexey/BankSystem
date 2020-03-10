package com.bank.customerservice.controller.DTO;

import com.bank.customerservice.entity.Customer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerResponseDTO {

    @NotNull
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String phone;

    @NotNull
    private String mail;

    public CustomerResponseDTO(Customer customer) {
        this.name = customer.getName();
        this.phone = customer.getPhone();
        this.mail = customer.getMail();
    }
}