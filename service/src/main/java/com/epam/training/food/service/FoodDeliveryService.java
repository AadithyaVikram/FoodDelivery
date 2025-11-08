package com.epam.training.food.service;

import com.epam.training.food.domain.Credentials;
import com.epam.training.food.domain.Customer;
import com.epam.training.food.domain.Food;
import com.epam.training.food.domain.Order;

import java.util.List;
public interface FoodDeliveryService {
    Customer authenticate(Credentials credentials) throws AuthenticationException;

    List<Food> listAllFood();

    void updateCart(Customer customer, Food food, int pieces) throws LowBalanceException;

    Order createOrder(Customer customer);

    List<Order> listOrders();

    Order getOrderById(long id);
}
