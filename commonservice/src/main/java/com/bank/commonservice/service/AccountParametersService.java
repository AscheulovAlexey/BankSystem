package com.bank.commonservice.service;

import com.bank.commonservice.controller.dto.BillListResponseDTO;
import com.bank.commonservice.controller.dto.BillResponseDTO;
import com.bank.commonservice.controller.dto.BillResponseGetBillDTO;
import com.bank.commonservice.controller.dto.CustomerResponseDTO;
import com.bank.commonservice.exception.BillNotBelongThisCustomerException;
import com.bank.commonservice.exception.BillNotFoundException;
import com.bank.commonservice.exception.CustomerNotFoundException;
import com.bank.commonservice.exception.NotEnoughMoneyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.List;

public class AccountParametersService {

    @Value("${customer.service.url}")
    private String customerUrl;

    @Value("${bill.service.url}")
    private String billUrl;

    @Value("${billByCustomer.service.url}")
    private String billByCustomerUrl;

    private final RestTemplate restTemplate;

    @Autowired
    public AccountParametersService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void checkParametersOneCustomer(Long customerId, Long billId, BigDecimal transaction){

        BillResponseGetBillDTO firstBill = getBill(billId);

        if (firstBill.getBalance().compareTo(transaction) == -1){
            throw new NotEnoughMoneyException("Not enough money on bill with id = " + billId +
                    ". Current balance = " + firstBill.getBalance());
        }

        if (firstBill.getCustomerId() != customerId){
            throw new BillNotBelongThisCustomerException("bill with id = " + billId +
                    " not belong customer with id = " + customerId);
        }

    }

//    public void checkParametersOneCustomer(Long customerId, Long firstBillId, Long secondBillId,
//                                           BigDecimal transaction){
//
//        BillResponseGetBillDTO firstBill = getBill(firstBillId);
//        BillResponseGetBillDTO secondBill = getBill(secondBillId);
//
//        if (firstBill.getBalance().compareTo(transaction) == -1){
//            throw new NotEnoughMoneyException("Not enough money on bill with id = " + firstBillId +
//                    ". Current balance = " + firstBill.getBalance());
//        }
//
//        if (firstBill.getCustomerId() != customerId){
//            throw new BillNotBelongThisCustomerException("bill with id = " + firstBillId +
//                    " not belong customer with id = " + customerId);
//        }
//
//        if (secondBill.getCustomerId() != customerId){
//            throw new BillNotBelongThisCustomerException("bill with id = " + firstBillId +
//                    " not belong customer with id = " + customerId);
//        }
//    }

    public CustomerResponseDTO getCustomer(Long customerId){

        StringBuilder customerURL = new StringBuilder(customerUrl);
        customerURL.append(customerId);
        ResponseEntity<CustomerResponseDTO> response = null;

        try {
            response = restTemplate.getForEntity(customerURL.toString(), CustomerResponseDTO.class);
        } catch (HttpClientErrorException ex){
            if (ex.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new CustomerNotFoundException("Can't find customer with id = " + customerId);
            }
        }
        return response.getBody();
    }

    public List<BillResponseDTO> getBills(Long customerId){

        StringBuilder billByCustomerURL = new StringBuilder(billByCustomerUrl);
        billByCustomerURL.append(customerId);
        BillListResponseDTO response = restTemplate.getForObject(billByCustomerURL.toString(), BillListResponseDTO.class);

        return response.getBills();

    }

    public BillResponseGetBillDTO getBill(Long billId){

        StringBuilder billURL = new StringBuilder(billUrl);
        billURL.append(billId);
        ResponseEntity<BillResponseGetBillDTO> response = null;

        try {
            response = restTemplate.getForEntity(billURL.toString(), BillResponseGetBillDTO.class);
        } catch (HttpClientErrorException ex){
            if (ex.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new BillNotFoundException("Can't find bill with id = " + billId);
            }
        }

        return response.getBody();
    }

}
