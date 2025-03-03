package br.com.devannis.webmarket.model.dto;

import br.com.devannis.webmarket.model.entity.Address;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public record ClientRequestDTO(
        @NotNull(message = "User id must be inserted")
        Long userId,

        @NotBlank(message = "Your name can not be empty")
        String clientName,

        @NotEmpty(message = "Your address can not be empty")
        List<AddressRequestDTO> addresses,

        @NotBlank(message = "Your ID Number can not be empty")
        @Size(max = 20, message = "The id number must have less than 20 characters")
        String idNumber
) {
}
