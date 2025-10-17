package com.nao.retail.orders.service;

import com.nao.retail.orders.dto.OrderRequest;
import com.nao.retail.orders.dto.OrderResponse;
import com.nao.retail.orders.entity.OrderEntity;
import com.nao.retail.orders.entity.OrderStatus;
import com.nao.retail.orders.exception.ResourceNotFoundException;
import com.nao.retail.orders.mapper.OrderMapper;
import com.nao.retail.orders.repository.OrderRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

/** Lógica de negocio para órdenes. */
@Service
public class OrderService {

    private final OrderRepository repo;

    public OrderService(OrderRepository repo) {
        this.repo = repo;
    }

    @Transactional
    public OrderResponse create(OrderRequest req) {
        OrderEntity e = OrderMapper.toEntity(req);
        e = repo.save(e);
        return OrderMapper.toResponse(e);
    }

    @Transactional(readOnly = true)
    public OrderResponse get(UUID id) {
        OrderEntity e = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found: " + id));
        return OrderMapper.toResponse(e);
    }

    @Transactional(readOnly = true)
    public Page<OrderResponse> list(OrderStatus status, Pageable pageable) {
        Page<OrderEntity> page = (status == null)
                ? repo.findAll(pageable)
                : repo.findByStatus(status, pageable);
        return page.map(OrderMapper::toResponse);
    }

    @Transactional
    public OrderResponse update(UUID id, OrderRequest req) {
        OrderEntity e = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found: " + id));
        OrderMapper.copyToEntity(req, e);
        return OrderMapper.toResponse(repo.save(e));
    }

    @Transactional
    public void delete(UUID id) {
        if (!repo.existsById(id)) {
            throw new ResourceNotFoundException("Order not found: " + id);
        }
        repo.deleteById(id);
    }

    @Transactional
    public OrderResponse updateStatus(UUID id, OrderStatus status) {
        OrderEntity e = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found: " + id));
        e.setStatus(status);
        return OrderMapper.toResponse(repo.save(e));
    }
}
