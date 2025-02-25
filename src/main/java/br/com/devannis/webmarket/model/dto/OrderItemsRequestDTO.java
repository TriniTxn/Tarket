package br.com.devannis.webmarket.model.dto;

public record OrderItemsRequestDTO(
        Long productId,
        int quantity
) {
}
