package br.com.devannis.webmarket.model.dto;

import br.com.devannis.webmarket.model.entity.CartItems;

import java.math.BigDecimal;

public record CartItemResponseDTO(
        Long productId,
        String productName,
        BigDecimal productPrice,
        int quantity
) {
    public CartItemResponseDTO(CartItems cartItems) {
        this(
                cartItems.getProduct().getProductId(),
                cartItems.getProduct().getProductName(),
                cartItems.getProduct().getProductPrice(),
                cartItems.getQuantity()
        );
    }
}
