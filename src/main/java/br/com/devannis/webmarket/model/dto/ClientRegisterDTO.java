package br.com.devannis.webmarket.model.dto;

import jakarta.validation.constraints.NotBlank;

public record ClientRegisterDTO(
        Long clientId,
        
        Long userId,

        @NotBlank(message = "Your name can not be empty")
        String clientName,

        @NotBlank(message = "Your address can not be empty")
        String clientAddress,

        @NotBlank(message = "Your ID Number can not be empty")
        String idNumber
) {
}
