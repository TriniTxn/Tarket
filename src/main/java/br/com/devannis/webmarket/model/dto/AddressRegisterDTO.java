package br.com.devannis.webmarket.model.dto;

import jakarta.validation.constraints.NotBlank;

public record AddressRegisterDTO(

        @NotBlank(message = "Zip code can not be empty")
        String zipCode,

        @NotBlank(message = "Street can not be empty")
        String street,

        @NotBlank(message = "City can not be empty")
        String city,

        @NotBlank(message = "State can not be empty")
        String state
) {
}
