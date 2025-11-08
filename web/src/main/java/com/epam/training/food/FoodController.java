package com.epam.training.food;

import com.epam.training.food.api.FoodServiceApi;
import com.epam.training.food.domain.Cart;
import com.epam.training.food.domain.Customer;
import com.epam.training.food.domain.Food;
import com.epam.training.food.domain.Order;
import com.epam.training.food.model.CartModel;
import com.epam.training.food.model.FoodModel;
import com.epam.training.food.model.OrderModel;
import com.epam.training.food.repository.CustomerRepository;
import com.epam.training.food.repository.FoodRepository;
import com.epam.training.food.service.FoodDeliveryService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@Controller
@RequestMapping("api")
public class FoodController implements FoodServiceApi {
    private final FoodRepository foodRepository;
    private final CustomerRepository customerRepository;
    private final ObjectMapper objectMapper;
    private final FoodDeliveryService foodDeliveryService;

    public FoodController(FoodRepository foodRepository, CustomerRepository customerRepository, ObjectMapper objectMapper, FoodDeliveryService foodDeliveryService) {
        this.foodRepository = foodRepository;
        this.customerRepository = customerRepository;
        this.objectMapper = objectMapper;
        this.foodDeliveryService = foodDeliveryService;
    }

    @GetMapping("foods")
    @Override
    public ResponseEntity<List<FoodModel>> getFoods() {
        List<Food> foods = foodDeliveryService.listAllFood();
        List<FoodModel> foodModels = objectMapper.convertValue(foods, new TypeReference<List<FoodModel>>() {
        });
        return ResponseEntity.ok(foodModels);
    }

    // added json ignore to customer field in order model
    @GetMapping("orders/{id}")
    public ResponseEntity<OrderModel> getOrderById(@PathVariable("id") long id){
        Order order=foodDeliveryService.getOrderById(id);
        OrderModel orderModel=objectMapper.convertValue(order, OrderModel.class);
        return ResponseEntity.ok(orderModel);
    }

    @GetMapping("orders")
    public ResponseEntity<List<OrderModel>> getOrders() {
        List<Order> orders = foodDeliveryService.listOrders();
        List<OrderModel> orderModels = objectMapper.convertValue(orders, new TypeReference<List<OrderModel>>() {});
        return ResponseEntity.ok(orderModels);
    }

    @PostMapping("orders")
    public ResponseEntity<OrderModel> createOrder(@RequestBody CartModel cartModel) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName(); // Get logged in username
        Converter converter=new Converter(objectMapper);
        Cart cart=converter.cartConverter(cartModel);
        Customer customer = customerRepository.findByUserName(username)
                .orElseThrow(() -> new NoSuchElementException("User not found"));

        customer.setCart(cart);
        Order order = foodDeliveryService.createOrder(customer);
        System.out.println(order);
        OrderModel orderModel=converter.orderConverter(order);
        return new ResponseEntity<>(orderModel, HttpStatus.CREATED);
    }

    @PostMapping("/updateCart")
    public ResponseEntity<String> updateCart(@RequestParam Long customerId, @RequestParam Long foodId, @RequestParam int pieces) {

        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid customer ID"));
        Food food = foodRepository.findById(foodId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid food ID"));
        foodDeliveryService.updateCart(customer, food, pieces);
        return ResponseEntity.ok("Cart updated successfully");
    }

}
