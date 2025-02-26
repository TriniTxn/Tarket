package br.com.devannis.webmarket.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record OrderRequestDTO(
        @NotEmpty(message = "The list of items can not be empty")
        List<OrderItemsRequestDTO> items,

        @NotBlank(message = "You must insert a valid payment method")
        String paymentMethod
) {
}
