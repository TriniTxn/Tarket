package br.com.devannis.webmarket.model.dto;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public record CartRequestDTO(
        @NotNull(message = "List of items can not be null")
        List<CartItemRequestDTO> items
) {
}
