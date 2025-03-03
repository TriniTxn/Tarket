package br.com.devannis.webmarket.mapper;

import br.com.devannis.webmarket.model.dto.CartItemResponseDTO;
import br.com.devannis.webmarket.model.entity.CartItems;

public class CartItemMapper {

    public static CartItemResponseDTO toDto(CartItems cartItems) {
        if (cartItems == null) {
            throw new IllegalArgumentException("Cart items cannot be null");
        }

        return new CartItemResponseDTO(
                cartItems.getProduct().getProductId(),
                cartItems.getProduct().getProductName(),
                cartItems.getProduct().getProductPrice(),
                cartItems.getQuantity()
        );
    }
}
