package com.epam.training.food.service;

import com.epam.training.food.domain.*;
import com.epam.training.food.repository.CustomerRepository;
import com.epam.training.food.repository.FoodRepository;
import com.epam.training.food.repository.OrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;

@Component
public class DefaultFoodDeliveryService implements FoodDeliveryService {


    private CustomerRepository customerRepository;
    private OrderRepository orderRepository;
    private FoodRepository foodRepository;

    public DefaultFoodDeliveryService(CustomerRepository customerRepository, OrderRepository orderRepository, FoodRepository foodRepository) {
        this.customerRepository = customerRepository;
        this.orderRepository = orderRepository;
        this.foodRepository = foodRepository;
    }



    @Override
    @Transactional
    public Customer authenticate(Credentials credentials) throws AuthenticationException{
         Customer customer= customerRepository.findCustomerByUserNameAndPassword(credentials.getUserName(),credentials.getPassword()).orElse(null);
         if(customer!=null) return customer;
         throw new AuthenticationException("Authentication invalid");
    }

    @Override
    @Transactional
    public List<Food> listAllFood() {

        return foodRepository.findAll();
    }

    @Override
    @Transactional
    public void updateCart(Customer customer, Food food, int pieces) throws LowBalanceException {
        OrderItem foodPresent = customer.getCart().getOrderItems().stream().filter(s -> s.getFood().equals(food)).findFirst().orElse(null);
        if (foodPresent == null && pieces <= 0) throw new IllegalArgumentException("invalid pieces");
        customer.getCart().getOrderItems().remove(foodPresent);

        BigDecimal price = food.getPrice().multiply(BigDecimal.valueOf(pieces));
        OrderItem orderItem = null;
        if (customer.getBalance().compareTo(price) >= 0) {

            if (foodPresent != null) {
                customer.getCart().getOrderItems().remove(foodPresent);

            }
            if (pieces > 0) {
                orderItem = new OrderItem(food, pieces, price);
                customer.getCart().getOrderItems().add(orderItem);
            }
            customer.getCart().setPrice(price);
            customerRepository.save(customer);
        } else {
            throw new LowBalanceException("Insufficient balance");
        }

    }

    @Override
    @Transactional
    public Order createOrder(Customer customer) {
        System.out.println(customer+"  "+customer.getCart());
        if (customer.getCart() == null || customer.getCart().getOrderItems().isEmpty())
            throw new IllegalArgumentException("Cart is empty");
        System.out.println(customer);
        Order order = new Order(customer);
        if(customer.getBalance().compareTo(order.getPrice())<0){
            throw new LowBalanceException("low Balance");
        }
        orderRepository.save(order);
        customer.setCart(Cart.getEmptyCart());
        customer.setBalance(customer.getBalance().subtract(order.getPrice()));
        customer.getOrders().add(order);
        customerRepository.save(customer);
        return order;
    }

    @Override
    @Transactional
    public List<Order> listOrders(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName(); // Get logged in username

        Customer customer = customerRepository.findByUserName(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return orderRepository.findByCustomer_Id(customer.getId());
    }

    @Override
    @Transactional
    public Order getOrderById(long id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName(); // Get logged in username

        Customer customer = customerRepository.findByUserName(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Order order=orderRepository.findById(id).orElse(null);
        if(order==null) throw new NoSuchElementException("No orders");
        if(order.getCustomer().getId()!=customer.getId()) throw new AccessDeniedException("No Access");
        return order;
    }
}
