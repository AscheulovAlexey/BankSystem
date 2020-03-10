package com.bank.bill.service;

import com.bank.bill.entity.Bill;
import com.bank.bill.exception.BillNotEnoughMoneyException;
import com.bank.bill.exception.BillNotFoundException;
import com.bank.bill.repository.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class BillService {

    private BillRepository billRepository;

    @Autowired
    public BillService(BillRepository billRepository) {
        this.billRepository = billRepository;
    }

    public Long createBill(Long customerId, BigDecimal balance){
        Bill bill = new Bill(customerId, balance);
        Bill newBill = billRepository.save(bill);

        return newBill.getId();
    }

    public void deleteBillById(Long id){
        billRepository.deleteById(id);
    }

    public Bill findBillById(Long id){
        return billRepository.findById(id).orElseThrow(() -> new BillNotFoundException("Can't find Bill with ID = " + id));
    }

    public List<Bill> findBillsByCustomerId(Long customerId){
        return billRepository.findAllBillsByCustomerId(customerId);
    }

    @Transactional
    public Bill commitAdjustment(Long id, BigDecimal adjustment){

        Bill bill = billRepository.findById(id).orElseThrow(() -> new BillNotFoundException("Can't find Bill with ID = " + id));
        bill.setBalance(bill.getBalance().add(adjustment));

        return billRepository.save(bill);
    }

    @Transactional
    public Bill commitPayment(Long id, BigDecimal payment){

        Bill bill = billRepository.findById(id).orElseThrow(() -> new BillNotFoundException("Can't find Bill with ID = " + id));
        if (bill.getBalance().compareTo(payment) < 0) {
            throw new BillNotEnoughMoneyException("Balance this ID = " + id + " less than payment");
        } else {
            bill.setBalance(bill.getBalance().subtract(payment));
        }

        return billRepository.save(bill);
    }

}
