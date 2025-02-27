package br.com.devannis.webmarket.model.dto;

import jakarta.validation.constraints.NotBlank;

public record PaymentMethodsRequestDTO(
        @NotBlank(message = "Payment method name must be informed")
        String paymentMethodName,

        String description
) {
}
