package br.com.devannis.webmarket.mapper;

import br.com.devannis.webmarket.model.dto.OrderItemsResponseDTO;
import br.com.devannis.webmarket.model.entity.OrderItems;

public class OrderItemsMapper {

    public static OrderItemsResponseDTO toDto(OrderItems orderItem) {
        return new OrderItemsResponseDTO(
                orderItem.getProduct().getProductId(),
                orderItem.getProduct().getProductName(),
                orderItem.getProduct().getProductPrice(),
                orderItem.getQuantity()
        );
    }
}
