package com.bank.commonservice.service;

import com.bank.commonservice.controller.dto.BillResponseGetBillDTO;
import com.bank.commonservice.controller.dto.transfer.AdjustmentDTO;
import com.bank.commonservice.controller.dto.transfer.PaymentDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.math.BigDecimal;

public class AccountMoneyService {

    @Value("${bill.payment.url}")
    private String billPaymentUrl;

    @Value("${bill.adjustment.url}")
    private String billAdjustmentUrl;

    private final RestTemplate restTemplate;
    private final HttpHeaders httpHeaders;
    private final ObjectMapper objectMapper;

    @Autowired
    public AccountMoneyService(RestTemplate restTemplate, HttpHeaders httpHeaders, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.httpHeaders = httpHeaders;
        this.objectMapper = objectMapper;
    }

    public ResponseEntity<String> postRequestPayment(Long firstBillId, BigDecimal transaction) {
        StringBuilder billPaymentURL = new StringBuilder(billPaymentUrl);
        billPaymentURL.append(firstBillId);

        PaymentDTO paymentDTO = new PaymentDTO(transaction);
        String paymentJSON = null;
        try {
            paymentJSON = objectMapper.writeValueAsString(paymentDTO);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entityPayment = new HttpEntity<>(paymentJSON, httpHeaders);

        return restTemplate.exchange(billPaymentURL.toString(), HttpMethod.PUT, entityPayment, String.class);
    }

    public ResponseEntity<String> postRequestAdjustment(Long secondBillId, BigDecimal transaction) {
        StringBuilder billAdjustmentURL = new StringBuilder(billAdjustmentUrl);
        billAdjustmentURL.append(secondBillId);

        AdjustmentDTO adjustmentDTO = new AdjustmentDTO(transaction);
        String adjustmentJSON = null;
        try {
            adjustmentJSON = objectMapper.writeValueAsString(adjustmentDTO);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entityAdjustment = new HttpEntity<>(adjustmentJSON, httpHeaders);

        return restTemplate.exchange(billAdjustmentURL.toString(), HttpMethod.PUT, entityAdjustment, String.class);
    }

    public BillResponseGetBillDTO getResponsePayment(ResponseEntity<String> responsePayment) throws IOException {
        return objectMapper.readValue(responsePayment.getBody(), BillResponseGetBillDTO.class);
    }

    public BillResponseGetBillDTO getResponseAdjustment(ResponseEntity<String> responseAdjustment) throws IOException {
        return objectMapper.readValue(responseAdjustment.getBody(), BillResponseGetBillDTO.class);
    }

}
