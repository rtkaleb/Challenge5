package com.meli.ordermanagement.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.meli.ordermanagement.entity.Customer;
import com.meli.ordermanagement.entity.Order;
import com.meli.ordermanagement.exception.ResourceNotFoundException;
import com.meli.ordermanagement.repository.OrderRepository;

@ExtendWith(MockitoExtension.class)
class OrderServiceImplTest {

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderServiceImpl orderService;

    private Order order;
    private Customer customer;

    @BeforeEach
    void setUp() {
        customer = new Customer();
        customer.setId(1L);
        customer.setFullName("Juan Ventura");

        order = new Order();
        order.setId(1L);
        order.setCustomer(customer);
        order.setStatus("PENDIENTE");
        order.setTotal(new BigDecimal("100.00"));
    }

    @DisplayName("JUnit test for getAllOrders method")
    @Test
    void givenOrdersList_whenGetAllOrders_thenReturnOrdersList() {
        // given - precondition or setup
        given(orderRepository.findAll()).willReturn(List.of(order));

        // when - action or the behaviour that we are going to test
        List<Order> orderList = orderService.getAllOrders();

        // then - verify the output
        assertThat(orderList).isNotNull();
        assertThat(orderList.size()).isEqualTo(1);
    }
    
    @DisplayName("JUnit test for getOrderById method (success case)")
    @Test
    void givenOrderId_whenGetOrderById_thenReturnOrderObject() {
        // given
        given(orderRepository.findById(1L)).willReturn(Optional.of(order));

        // when
        Order savedOrder = orderService.getOrderById(1L).get();

        // then
        assertThat(savedOrder).isNotNull();
    }

    @DisplayName("JUnit test for getOrderById method (failure case)")
    @Test
    void givenNonExistentOrderId_whenGetOrderById_thenReturnsEmptyOptional() {
        // given
        given(orderRepository.findById(99L)).willReturn(Optional.empty());

        // when
        Optional<Order> result = orderService.getOrderById(99L);

        // then
        assertThat(result).isEmpty();
    }

    @DisplayName("JUnit test for createOrder method")
    @Test
    void givenOrderObject_whenCreateOrder_thenReturnSavedOrder() {
        // given
        given(orderRepository.save(any(Order.class))).willReturn(order);
        
        // when
        Order savedOrder = orderService.createOrder(new Order());

        // then
        assertThat(savedOrder).isNotNull();
    }
    
    @DisplayName("JUnit test for updateOrder method (success case)")
    @Test
    void givenOrderIdAndDetails_whenUpdateOrder_thenReturnUpdatedOrder() {
        // given
        given(orderRepository.findById(1L)).willReturn(Optional.of(order));
        given(orderRepository.save(any(Order.class))).willReturn(order);
        
        Order newDetails = new Order();
        newDetails.setStatus("ENVIADO");
        newDetails.setTotal(new BigDecimal("150.00"));
        newDetails.setShippingAddress("New Address");
        newDetails.setCustomer(customer);
        
        // when
        Order updatedOrder = orderService.updateOrder(1L, newDetails);
        
        // then
        assertThat(updatedOrder).isNotNull();
        assertThat(updatedOrder.getStatus()).isEqualTo("ENVIADO");
        assertThat(updatedOrder.getTotal()).isEqualTo(new BigDecimal("150.00"));
    }

    @DisplayName("JUnit test for updateOrder method (failure case - not found)")
    @Test
    void givenNonExistentOrderId_whenUpdateOrder_thenThrowsResourceNotFoundException() {
        // given
        given(orderRepository.findById(99L)).willReturn(Optional.empty());
        Order newDetails = new Order();

        // when & then
        assertThrows(ResourceNotFoundException.class, () -> {
            orderService.updateOrder(99L, newDetails);
        });
    }
}