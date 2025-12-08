package com.moses.s3_upload_api.customer;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    // Check if email is already registered
    boolean existsCustomerByEmail(String email);

    // Check if customer ID exists
    boolean existsCustoemrById(Integer id);


    // Find customer by email (returns empty if not found)
    Optional<Customer> findCustomerByEmail(String email);

}
