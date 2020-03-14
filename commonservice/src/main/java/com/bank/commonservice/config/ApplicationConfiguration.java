package com.bank.commonservice.config;

import com.bank.commonservice.service.CheckAccountParametersService;
import com.bank.commonservice.service.AccountParametersService;
import com.bank.commonservice.service.AccountMoneyService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

@Configuration
@PropertySource("classpath:url-properties.properties")
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
    public AccountParametersService getAccountParametersService(){
        return new AccountParametersService(restTemplate());
    }

    @Bean
    public AccountMoneyService transferAccountMoneyService(){
        return new AccountMoneyService(restTemplate(), httpHeaders(), objectMapper());
    }
}
