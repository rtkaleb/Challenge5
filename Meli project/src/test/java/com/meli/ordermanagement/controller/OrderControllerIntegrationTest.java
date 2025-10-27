package com.meli.ordermanagement.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.meli.ordermanagement.entity.Customer;
import com.meli.ordermanagement.entity.Order;
import com.meli.ordermanagement.repository.CustomerRepository;
import com.meli.ordermanagement.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.math.BigDecimal;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("test") // Ensures we use the application-test.yml configuration (H2 database)
class OrderControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private Customer savedCustomer;

    @BeforeEach
    void setup() {
        // Limpiamos las tablas en el orden correcto para evitar problemas de 'foreign key'
        orderRepository.deleteAll();
        customerRepository.deleteAll();
        
        Customer customer = new Customer();
        customer.setFullName("Test Customer");
        customer.setEmail("test@customer.com");
        savedCustomer = customerRepository.save(customer);
    }

    @DisplayName("Integration test for POST /api/v1/orders (create order)")
    @Test
    void givenOrderObject_whenCreateOrder_thenReturnSavedOrder() throws Exception {
        // given
        Order order = new Order();
        order.setCustomer(savedCustomer);
        order.setStatus("PENDIENTE");
        order.setShippingAddress("123 Test St");
        order.setTotal(new BigDecimal("199.99"));

        // when
        ResultActions response = mockMvc.perform(post("/api/v1/orders")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(order)));

        // then
        response.andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.customer.fullName", is(savedCustomer.getFullName())))
                .andExpect(jsonPath("$.status", is("PENDIENTE")));
    }

    @DisplayName("Integration test for GET /api/v1/orders (get all orders)")
    @Test
    void givenListOfOrders_whenGetAllOrders_thenReturnOrdersList() throws Exception {
        // given
        Order order1 = new Order();
        order1.setCustomer(savedCustomer);
        order1.setStatus("PENDIENTE");
        order1.setShippingAddress("123 Test St");
        order1.setTotal(new BigDecimal("100.00"));
        
        Order order2 = new Order();
        order2.setCustomer(savedCustomer);
        order2.setStatus("ENVIADO");
        order2.setShippingAddress("456 Test Ave");
        order2.setTotal(new BigDecimal("200.00"));

        orderRepository.saveAll(List.of(order1, order2));

        // when
        ResultActions response = mockMvc.perform(get("/api/v1/orders"));

        // then
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.size()", is(2)));
    }
    
    @DisplayName("Integration test for GET /api/v1/orders/{id} (not found)")
    @Test
    void givenInvalidOrderId_whenGetOrderById_thenReturnNotFound() throws Exception {
        // when
        ResultActions response = mockMvc.perform(get("/api/v1/orders/{id}", 9999L)); // Usar un ID que seguro no existe

        // then
        response.andExpect(status().isNotFound())
                .andDo(print());
    }
}