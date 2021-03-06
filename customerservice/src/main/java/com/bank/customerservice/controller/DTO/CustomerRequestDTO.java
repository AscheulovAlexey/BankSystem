package com.bank.customerservice.controller.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerRequestDTO {

    @NotNull(message = "Should be initialized")
    private String name;

    @NotNull(message = "Should be initialized")
    private String phone;

    @NotNull @Email(message = "Should be mail")
    private String mail;

    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null && getClass()!= o.getClass()) return false;

        CustomerRequestDTO cr = (CustomerRequestDTO) o;
        return name == cr.getName();
    }
}
