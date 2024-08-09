package com.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.pojos.Customer;

public interface CustomerRepository extends JpaRepository <Customer, Long>{
	// Custom query method to find customers by city
    List<Customer> findByCity(String city);

}
