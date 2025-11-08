package com.epam.training.food;

import com.epam.training.food.domain.Cart;
import com.epam.training.food.domain.Food;
import com.epam.training.food.domain.Order;
import com.epam.training.food.domain.OrderItem;
import com.epam.training.food.model.CartModel;
import com.epam.training.food.model.FoodModel;
import com.epam.training.food.model.OrderItemModel;
import com.epam.training.food.model.OrderModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class Converter {

    private final ObjectMapper objectMapper;

    public Converter(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;

    }

    public Cart cartConverter(CartModel cartModel) {
        Cart cart = new Cart();
        List<OrderItem> orderItems = new ArrayList<>();
        cart.setPrice(cartModel.getPrice());
        List<OrderItemModel> cartOrderItemsModel = cartModel.getOrderItemModels();
        for (OrderItemModel oim : cartOrderItemsModel) {
            Food food = objectMapper.convertValue(oim.getFoodModel(), Food.class);
            OrderItem orderItem = new OrderItem(food, oim.getPieces(), oim.getPrice());
            orderItem.setId(1L);
            orderItems.add(orderItem);
        }
        cart.setOrderItems(orderItems);
        return cart;
    }

    public OrderModel orderConverter(Order order){
        OrderModel orderModel = new OrderModel();
        List<OrderItemModel> orderItemModels= new ArrayList<>();
        orderModel.setId(order.getId());
        orderModel.setPrice(order.getPrice());
        orderModel.setTimestampCreated(order.getTimestampCreated().toString());
        for(OrderItem oi:order.getOrderItems()){
            OrderItemModel orderItemModel= new OrderItemModel();
            orderItemModel.setId(1L);
            orderItemModel.setPrice(oi.getPrice());
            orderItemModel.setPieces(oi.getPieces());
            orderItemModel.setFoodModel(objectMapper.convertValue(oi.getFood(), FoodModel.class));
            orderItemModels.add(orderItemModel);
        }
        orderModel.setOrderItemModels(orderItemModels);
        return orderModel;
    }
}
