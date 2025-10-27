package com.meli.ordermanagement.service;

import com.meli.ordermanagement.entity.Order;
import java.util.List;
import java.util.Optional;

public interface OrderService {
    Order createOrder(Order order);
    List<Order> getAllOrders();
    Optional<Order> getOrderById(Long id);
    Order updateOrder(Long id, Order orderDetails);
    void deleteOrder(Long id);
}
