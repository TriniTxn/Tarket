package br.com.devannis.webmarket.model.dto;

import java.util.List;

public record CartResponseDTO(
        Long cartId,
        double cartTotal,
        List<CartItemDTO> items
) {
}
