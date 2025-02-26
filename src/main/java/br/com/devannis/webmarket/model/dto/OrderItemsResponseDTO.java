package br.com.devannis.webmarket.model.dto;

import java.math.BigDecimal;

public record OrderItemsResponseDTO(
        Long productId,
        String productName,
        BigDecimal productPrice,
        int quantity
) {
}
