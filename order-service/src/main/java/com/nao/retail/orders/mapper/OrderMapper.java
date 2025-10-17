package com.nao.retail.orders.mapper;

import com.nao.retail.orders.dto.OrderItemDTO;
import com.nao.retail.orders.dto.OrderRequest;
import com.nao.retail.orders.dto.OrderResponse;
import com.nao.retail.orders.entity.OrderEntity;
import com.nao.retail.orders.entity.OrderItem;

import java.util.List;
import java.util.stream.Collectors;

/** Conversión entre Entities y DTOs. */
public class OrderMapper {

    public static OrderEntity toEntity(OrderRequest req) {
        OrderEntity e = new OrderEntity();
        copyToEntity(req, e);
        return e;
    }

    public static void copyToEntity(OrderRequest req, OrderEntity e) {
        e.setCustomerName(req.getCustomerName());
        e.setCustomerEmail(req.getCustomerEmail());
        e.setItems(toItems(req.getItems()));
        e.setTotalAmount(req.getTotalAmount());
    }

    public static OrderResponse toResponse(OrderEntity e) {
        OrderResponse r = new OrderResponse();
        r.setId(e.getId());
        r.setCustomerName(e.getCustomerName());
        r.setCustomerEmail(e.getCustomerEmail());
        r.setItems(toItemDTOs(e.getItems()));
        r.setTotalAmount(e.getTotalAmount());
        r.setStatus(e.getStatus());
        r.setCreatedAt(e.getCreatedAt());
        r.setUpdatedAt(e.getUpdatedAt());
        return r;
    }

    private static List<OrderItem> toItems(List<OrderItemDTO> dtos) {
        return dtos.stream().map(d -> {
            OrderItem i = new OrderItem();
            i.setSku(d.getSku());
            i.setName(d.getName());
            i.setQuantity(d.getQuantity());
            i.setUnitPrice(d.getUnitPrice());
            return i;
        }).collect(Collectors.toList());
    }

    private static List<OrderItemDTO> toItemDTOs(List<OrderItem> items) {
        return items.stream().map(i -> {
            OrderItemDTO d = new OrderItemDTO();
            d.setSku(i.getSku());
            d.setName(i.getName());
            d.setQuantity(i.getQuantity());
            d.setUnitPrice(i.getUnitPrice());
            return d;
        }).collect(Collectors.toList());
    }
}
