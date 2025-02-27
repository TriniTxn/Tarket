package br.com.devannis.webmarket.model.dto;

import br.com.devannis.webmarket.model.entity.PaymentMethods;
import br.com.devannis.webmarket.model.enums.PaymentMethodType;

public record PaymentMethodsResponseDTO(
        Long PaymentMethodId,
        PaymentMethodType paymentMethodName,
        String description
) {
    public PaymentMethodsResponseDTO(PaymentMethods paymentMethod) {
        this(
                paymentMethod.getPaymentMethodId(),
                paymentMethod.getPaymentMethodName(),
                paymentMethod.getDescription()
        );
    }
}
