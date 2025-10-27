package com.meli.ordermanagement.repository;

import com.meli.ordermanagement.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio para acceder a los datos de las órdenes.
 * Extiende JpaRepository para obtener operaciones CRUD básicas y más.
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    // Aquí se pueden agregar métodos para consultas personalizadas si es necesario.
    // Por ejemplo: List<Order> findByStatus(String status);
}
