package br.com.devannis.webmarket.model.dto;

public record OrderItemsResponseDTO(
        Long productId,
        String productName,
        double productPrice,
        int quantity
) {
}
