package br.com.devannis.webmarket.model.dto;

import java.math.BigDecimal;
import java.util.List;

public record OrderResponseDTO(
        Long orderId,
        String status,
        BigDecimal totalValue,
        String orderDate,
        List<OrderItemsResponseDTO> items
) {

}
