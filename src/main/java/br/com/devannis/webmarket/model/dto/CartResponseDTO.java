package br.com.devannis.webmarket.model.dto;

import br.com.devannis.webmarket.model.entity.Cart;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record CartResponseDTO(
        Long cartId,
        String status,
        BigDecimal totalValue,
        LocalDateTime creationAt,
        LocalDateTime updatedAt,
        List<CartItemResponseDTO> items
) {
    public CartResponseDTO(Cart cart) {
        this(
                cart.getCartId(),
                cart.getStatus(),
                cart.getCartTotal(),  // Método para calcular o valor total do carrinho
                cart.getCreatedAt(),
                cart.getUpdatedAt(),
                cart.getCartItems().stream()
                        .map(CartItemResponseDTO::new)
                        .toList()  // Converte a lista de CartItem para CartItemResponseDTO
        );
    }
}
