package com.app.service;

import java.util.List;

import com.app.dto.CustomerDto;
import com.app.pojos.Customer;

public interface ICustomerService {

    CustomerDto createCustomer(CustomerDto customerDto);

    CustomerDto getCustomerById(Long id);

    CustomerDto updateCustomer(Long id, CustomerDto customerDto);

    void deleteCustomer(Long id);

    List<Customer> getCustomersByCity(String city);
}
