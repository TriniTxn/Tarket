package br.com.devannis.webmarket.model.dto;

// This record is for update or remove an item from the cart
public record CartItemRequestDTO(
        Long productId,
        int quantity
) {
}
