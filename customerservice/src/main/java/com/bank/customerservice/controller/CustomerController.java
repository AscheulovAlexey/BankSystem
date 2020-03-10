package com.bank.customerservice.controller;

import com.bank.customerservice.controller.DTO.CustomerRequestDTO;
import com.bank.customerservice.controller.DTO.CustomerResponseDTO;
import com.bank.customerservice.entity.Customer;
import com.bank.customerservice.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CustomerController {

    private CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/customer/create")
    public Long createCustomer(@RequestBody @Valid CustomerRequestDTO customerRequestDTO){

        return customerService.createCustomer(customerRequestDTO.getName(),
                customerRequestDTO.getPhone(),
                customerRequestDTO.getMail());
    }

    @DeleteMapping("/customer/delete/{id}")
    public void deleteCustomer(@PathVariable @NotNull Long id){
        customerService.deleteCustomer(id);
    }

    @PutMapping("/customer/update/{id}")
    public Customer updateCustomer(@PathVariable @NotNull Long id,
                                  @RequestBody @Valid CustomerRequestDTO customerRequestDTO){

        return customerService.updateCustomer(id,
                customerRequestDTO.getName(),
                customerRequestDTO.getPhone(),
                customerRequestDTO.getMail());
    }

    @GetMapping("/customer/{id}")
    public CustomerResponseDTO getCustomerById(@PathVariable Long id){
        Customer customer = customerService.findCustomerById(id);
        return new CustomerResponseDTO(customer.getId(), customer.getName(), customer.getPhone(), customer.getMail() );
    }

    @GetMapping("/customer/all")
    public List<CustomerResponseDTO> getAllCustomers(){
        List<Customer> customers = customerService.findAllCustomers();
        return customers.stream().map(CustomerResponseDTO::new).collect(Collectors.toList());
    }
}



