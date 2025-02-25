package br.com.devannis.webmarket.model.dto;

import br.com.devannis.webmarket.model.entity.Order;

import java.util.List;
import java.util.stream.Collectors;

public record OrderResponseDTO(
        Long orderId,
        String status,
        double totalValue,
        String orderDate,
        List<OrderItemsResponseDTO> items
) {

}
