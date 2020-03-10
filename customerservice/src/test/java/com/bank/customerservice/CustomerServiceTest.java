package com.bank.customerservice;

import com.bank.customerservice.controller.DTO.CustomerResponseDTO;
import com.bank.customerservice.entity.Customer;
import com.bank.customerservice.repository.CustomerRepository;
import com.bank.customerservice.service.CustomerService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CustomerServiceTest  {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public void findById_ExpectedCustomer(){
        Customer customer = customerRepository.save(new Customer("lori", "+79991119911",
                "customer@mail.ru"));

        ResponseEntity<CustomerResponseDTO> response = testRestTemplate.getForEntity("http://localhost:" + port +
                "/customer/" + customer.getId(), CustomerResponseDTO.class);

        CustomerResponseDTO body = response.getBody();

        Assert.assertEquals(body.getName(), "lori");
        Assert.assertEquals(body.getPhone(), "+79991119911");
        Assert.assertEquals(body.getMail(), "customer@mail.ru");
    }

}
