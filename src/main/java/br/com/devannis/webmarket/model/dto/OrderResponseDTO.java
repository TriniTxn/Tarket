package br.com.devannis.webmarket.model.dto;

import br.com.devannis.webmarket.model.entity.Order;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public record OrderResponseDTO(
        Long orderId,
        String status,
        BigDecimal totalValue,
        LocalDateTime orderDate,
        List<OrderItemsResponseDTO> items
) {

}
