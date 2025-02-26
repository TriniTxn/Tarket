package br.com.devannis.webmarket.model.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record OrderItemsRequestDTO(
        @NotNull(message = "Product id must be informed")
        Long productId,

        @Min(value = 1, message = "Quantity must be greater than zero")
        int quantity
) {
}
