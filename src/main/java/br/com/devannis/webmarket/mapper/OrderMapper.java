package br.com.devannis.webmarket.mapper;

import br.com.devannis.webmarket.model.dto.OrderItemsResponseDTO;
import br.com.devannis.webmarket.model.dto.OrderResponseDTO;
import br.com.devannis.webmarket.model.entity.Order;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class OrderMapper {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

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

        String formattedOrderDate = order.getOrderDate().format(DATE_FORMATTER);

        return new OrderResponseDTO(
                order.getOrderId(),
                order.getStatus(),
                order.calculateTotalValue(),
                formattedOrderDate,
                items
        );
    }
}
