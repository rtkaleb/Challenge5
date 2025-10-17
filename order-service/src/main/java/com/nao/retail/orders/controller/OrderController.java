package com.nao.retail.orders.controller;

import com.nao.retail.orders.dto.OrderRequest;
import com.nao.retail.orders.dto.OrderResponse;
import com.nao.retail.orders.dto.UpdateStatusRequest;
import com.nao.retail.orders.entity.OrderStatus;
import com.nao.retail.orders.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * API REST para gestión de órdenes.
 * Base path: /api/v1/orders
 */
@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    private final OrderService service;

    public OrderController(OrderService service) { this.service = service; }

    /** Crear orden */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderResponse create(@Valid @RequestBody OrderRequest req) {
        return service.create(req);
    }

    /** Obtener por id */
    @GetMapping("/{id}")
    public OrderResponse get(@PathVariable UUID id) {
        return service.get(id);
    }

    /** Listar con paginación y filtro opcional por estado */
    @GetMapping
    public Page<OrderResponse> list(
            @RequestParam(required = false) OrderStatus status,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return service.list(status, pageable);
    }

    /** Reemplazo total de la orden */
    @PutMapping("/{id}")
    public OrderResponse update(@PathVariable UUID id, @Valid @RequestBody OrderRequest req) {
        return service.update(id, req);
    }

    /** Cambiar sólo el estado */
    @PatchMapping("/{id}/status")
    public OrderResponse updateStatus(@PathVariable UUID id, @Valid @RequestBody UpdateStatusRequest req) {
        return service.updateStatus(id, req.getStatus());
    }

    /** Borrar */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }
}
