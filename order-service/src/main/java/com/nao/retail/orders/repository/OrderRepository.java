package com.nao.retail.orders.repository;

import com.nao.retail.orders.entity.OrderEntity;
import com.nao.retail.orders.entity.OrderStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/** Acceso a datos Order. */
public interface OrderRepository extends JpaRepository<OrderEntity, UUID> {
    Page<OrderEntity> findByStatus(OrderStatus status, Pageable pageable);
}
