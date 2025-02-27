package br.com.devannis.webmarket.mapper;

import br.com.devannis.webmarket.model.dto.CartItemResponseDTO;
import br.com.devannis.webmarket.model.dto.CartResponseDTO;
import br.com.devannis.webmarket.model.entity.Cart;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class CartMapper {
    public static CartResponseDTO toDto(Cart cart) {
        List<CartItemResponseDTO> items = cart.getCartItems().stream()
                .map(item -> new CartItemResponseDTO(
                        item.getProduct().getProductId(),
                        item.getProduct().getProductName(),
                        item.getProduct().getProductPrice(),
                        item.getQuantity()
                ))
                .collect(Collectors.toList());

        BigDecimal totalValue = cart
                .getCartItems()
                .stream()
                .map(item -> item.getProduct().getProductPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

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
