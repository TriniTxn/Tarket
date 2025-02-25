package br.com.devannis.webmarket.model.dto;

public record CartRequestDTO(
        Long productId,
        int quantity
) {
}
