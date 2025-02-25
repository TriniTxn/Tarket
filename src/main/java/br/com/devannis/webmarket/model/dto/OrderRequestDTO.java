package br.com.devannis.webmarket.model.dto;

import java.util.List;

public record OrderRequestDTO(
        List<OrderItemsRequestDTO> items,
        String paymentMethod
) {
}
