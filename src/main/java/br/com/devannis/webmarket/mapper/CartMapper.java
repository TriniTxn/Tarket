package br.com.devannis.webmarket.mapper;

import br.com.devannis.webmarket.model.dto.CartItemResponseDTO;
import br.com.devannis.webmarket.model.dto.CartResponseDTO;
import br.com.devannis.webmarket.model.entity.Cart;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class CartMapper {
    public static CartResponseDTO toDto(Cart cart) {

        if (cart == null) {
            throw new IllegalArgumentException("Cart cannot be null");
        }

        List<CartItemResponseDTO> items = cart.getCartItems() != null ? cart.getCartItems()
                .stream()
                .filter(item -> item != null && item.getProduct() != null)
                .map(item -> new CartItemResponseDTO(
                        item.getProduct().getProductId(),
                        item.getProduct().getProductName(),
                        item.getProduct().getProductPrice(),
                        item.getQuantity()
                ))
                .collect(Collectors.toList()) : List.of();

        BigDecimal totalValue = cart
                .getCartItems() != null ? cart.getCartItems()
                .stream()
                .filter(item -> item != null && item.getProduct() != null)
                .map(item -> item.getProduct().getProductPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add) : BigDecimal.ZERO;

        return new CartResponseDTO(
                cart.getCartId(),
                cart.getStatus(),
                totalValue,
                cart.getCreatedAt(),
                cart.getUpdatedAt(),
                items
        );
    }
}
