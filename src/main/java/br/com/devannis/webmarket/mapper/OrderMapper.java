package br.com.devannis.webmarket.mapper;

import br.com.devannis.webmarket.model.dto.OrderItemsResponseDTO;
import br.com.devannis.webmarket.model.dto.OrderResponseDTO;
import br.com.devannis.webmarket.model.entity.Order;

import java.util.List;

public class OrderMapper {
    public static OrderResponseDTO toDto(Order order) {
        List<OrderItemsResponseDTO> items = order
                .getOrderItems()
                .stream()
                .map(item -> new OrderItemsResponseDTO(
                        item.getProduct().getProductId(),
                        item.getProduct().getProductName(),
                        item.getProduct().getProductPrice(),
                        item.getQuantity()
                ))
                .toList();

        return new OrderResponseDTO(
                order.getOrderId(),
                order.getStatus(),
                order.getTotalValue(),
                order.getOrderDate().toString(),
                items
        );
    }
}
