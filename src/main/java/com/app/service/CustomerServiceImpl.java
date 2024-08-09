package com.app.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dto.CustomerDto;
import com.app.exception.ResourceNotFoundException;
import com.app.pojos.Customer;
import com.app.repository.CustomerRepository;
import com.app.service.ICustomerService;

@Service
public class CustomerServiceImpl implements ICustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public CustomerDto createCustomer(CustomerDto customerDto) {
        Customer customer = mapper.map(customerDto, Customer.class);
        Customer savedCustomer = customerRepository.save(customer);
        return mapper.map(savedCustomer, CustomerDto.class);
    }

    @Override
    public CustomerDto getCustomerById(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer id"+id));
        return mapper.map(customer, CustomerDto.class);
    }

    @Override
    public CustomerDto updateCustomer(Long id, CustomerDto customerDto) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer id"+id));
        mapper.map(customerDto, customer);
        Customer updatedCustomer = customerRepository.save(customer);
        return mapper.map(updatedCustomer, CustomerDto.class);
    }

    @Override
    public void deleteCustomer(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer id"+id));
        customerRepository.delete(customer);
    }

    @Override
    public List<Customer> getCustomersByCity(String city) {
        return customerRepository.findByCity(city);
    }
}
