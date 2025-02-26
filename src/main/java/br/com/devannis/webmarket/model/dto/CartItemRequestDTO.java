package br.com.devannis.webmarket.model.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

// This record is for update or remove an item from the cart
public record CartItemRequestDTO(
        @NotNull(message = "Product id must be inserted")
        Long productId,

        @Min(value = 1, message = "The quantity must be greater than 0")
        int quantity
) {
}
