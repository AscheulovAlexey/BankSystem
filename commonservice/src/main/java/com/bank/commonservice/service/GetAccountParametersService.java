package com.bank.commonservice.service;

import com.bank.commonservice.controller.dto.BillResponseDTO;
import com.bank.commonservice.controller.dto.BillResponseGetBillDTO;
import com.bank.commonservice.controller.dto.CustomerResponseDTO;
import com.bank.commonservice.exception.BillNotFoundException;
import com.bank.commonservice.exception.CustomerNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

public class GetAccountParametersService {

    private static final String CUSTOMER_URL = "http://localhost:8080/customer/";
    private static final String BILL_URL = "http://localhost:8081/bill/";
    private static final String BILLS_BY_CUSTOMER_URL = "http://localhost:8081/bill/customer";

    private final RestTemplate restTemplate;

    @Autowired
    public GetAccountParametersService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public CustomerResponseDTO getCustomer(Long customerId){

        StringBuilder customerURL = new StringBuilder(CUSTOMER_URL);
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

    public BillResponseDTO[] getBills(Long customerId){

        StringBuilder billURL = new StringBuilder(BILLS_BY_CUSTOMER_URL);
        billURL.append(customerId);

        ResponseEntity<BillResponseDTO[]> response = restTemplate.
                getForEntity(billURL.toString(), BillResponseDTO[].class);

        return response.getBody();
    }

    public BillResponseGetBillDTO getBill(Long billId){

        StringBuilder billURL = new StringBuilder(BILL_URL);
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
