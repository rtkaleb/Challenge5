package com.nao.retail.orders.dto;

import com.nao.retail.orders.entity.OrderStatus;
import jakarta.validation.constraints.NotNull;

/** Payload mínimo para actualizar sólo el estado. */
public class UpdateStatusRequest {
    @NotNull
    private OrderStatus status;

    public OrderStatus getStatus() { return status; }
    public void setStatus(OrderStatus status) { this.status = status; }
}
