package com.bank.customerservice.service;

import com.bank.customerservice.entity.Customer;
import com.bank.customerservice.exception.CustomerNotFoundException;
import com.bank.customerservice.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Long createCustomer(String name, String phone, String mail){
        Customer customer = new Customer (name, phone, mail);
        return customerRepository.save(customer).getId();
    }

    public void deleteCustomer(Long id){
        customerRepository.deleteById(id);
    }

    public Customer findCustomerById(Long id){
        return customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Can't find customer with id = " + id )) ;
    }

    public Customer updateCustomer(Long id, String name, String phone, String mail){
        Customer customerFromDB = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Can't find customer with id = " + id )) ;;
        customerFromDB.setName(name);
        customerFromDB.setPhone(phone);
        customerFromDB.setMail(mail);
        customerRepository.save(customerFromDB);

        return customerFromDB;
    }

    public List<Customer> findAllCustomers(){
        List<Customer> customers = customerRepository.findAll();
        return customers;
    }

}
