package com.bank.bill.controller;

import com.bank.bill.controller.dto.*;
import com.bank.bill.entity.Bill;
import com.bank.bill.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RestController
public class BillController {

    private BillService billService;

    @Autowired
    public BillController(BillService billService) {
        this.billService = billService;
    }

    @PostMapping("/bill/create")
    public Long createBill(@RequestBody BillRequestDTO billRequestDTO) {
        return billService.createBill(billRequestDTO.getCustomerId(), billRequestDTO.getBalance());
    }

    @DeleteMapping("/bill/delete/{id}")
    public void deleteBill(@PathVariable Long id) {
        billService.deleteBillById(id);
    }

    @GetMapping("/bill/{id}")
    public BillResponseGetBillDTO getBill(@PathVariable Long id){
        Bill billFromBD = billService.findBillById(id);
        return new BillResponseGetBillDTO(billFromBD.getCustomerId(), billFromBD.getBalance());
    }

    @GetMapping("/bill/customer{customerId}")
    public BillListResponseGetByCustomerDTO getBillsByCustomerId(@PathVariable Long customerId){
        ArrayList<Bill> bills = (ArrayList<Bill>) billService.findBillsByCustomerId(customerId);

        List<BillResponseGetByCustomerDTO> billsResponseDTO = new ArrayList<>(bills.size());
        for (int i = 0; i < bills.size(); i++){
            billsResponseDTO.add(new BillResponseGetByCustomerDTO(bills.get(i).getId(), bills.get(i).getBalance()));
        }

        return new BillListResponseGetByCustomerDTO(billsResponseDTO);
    }

    @PutMapping("/bill/adjustment/{id}")
    public TransferBetweenBillsDTO commitAdjustment(@PathVariable Long id, @RequestBody AdjustmentDTO adjustment) {
        Bill bill = billService.commitAdjustment(id, adjustment.getAdjustment());

        return new TransferBetweenBillsDTO(bill.getId(), bill.getCustomerId(), bill.getBalance());
    }

    @PutMapping("/bill/payment/{id}")
    public TransferBetweenBillsDTO commitPayment(@PathVariable Long id, @RequestBody PaymentDTO payment) {
        Bill bill = billService.commitPayment(id, payment.getPayment());

        return new TransferBetweenBillsDTO(bill.getId(), bill.getCustomerId(), bill.getBalance());
    }

}


