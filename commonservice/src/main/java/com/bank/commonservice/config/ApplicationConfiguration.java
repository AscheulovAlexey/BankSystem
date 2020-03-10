package com.bank.commonservice.config;

import com.bank.commonservice.service.CheckAccountParametersService;
import com.bank.commonservice.service.GetAccountParametersService;
import com.bank.commonservice.service.TransferAccountMoneyService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationConfiguration {

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @Bean
    public ObjectMapper objectMapper(){
        return new ObjectMapper();
    }

    @Bean
    public HttpHeaders httpHeaders(){
        return new HttpHeaders();
    }

    @Bean
    public CheckAccountParametersService checkAccountParametersService(){
        return new CheckAccountParametersService(getAccountParametersService());
    }

    @Bean
    public GetAccountParametersService getAccountParametersService(){
        return new GetAccountParametersService(restTemplate());
    }

    @Bean
    public TransferAccountMoneyService transferAccountMoneyService(){
        return new TransferAccountMoneyService(restTemplate(), httpHeaders(), objectMapper());
    }
}
