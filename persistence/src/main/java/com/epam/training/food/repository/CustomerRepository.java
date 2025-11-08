package com.epam.training.food.repository;

import com.epam.training.food.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
    Optional<Customer> findCustomerByUserNameAndPassword(String userName, String password);

    Optional<Customer> findByUserName(String username);
}
