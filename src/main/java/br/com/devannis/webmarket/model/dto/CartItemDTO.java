package br.com.devannis.webmarket.model.dto;

import br.com.devannis.webmarket.model.entity.Product;

public record CartItemDTO(
        Long productId,
        String productName,
        double productPrice,
        int quantity
) {
}
