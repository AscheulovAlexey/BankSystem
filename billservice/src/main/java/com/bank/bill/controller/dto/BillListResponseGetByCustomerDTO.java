package com.bank.bill.controller.dto;

import com.bank.bill.controller.dto.BillResponseGetByCustomerDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BillListResponseGetByCustomerDTO {

    private List<BillResponseGetByCustomerDTO> bills;

}
