package com.epam.training.food.repository;

import com.epam.training.food.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order,Long> {
    List<Order> findByCustomer_Id(long customerId);


}
