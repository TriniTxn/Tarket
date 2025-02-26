package br.com.devannis.webmarket.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record AddressRequestDTO(

        @NotBlank(message = "Zip code can not be empty")
        @Size(max = 10, message = "Zip code must have less than 10 characters")
        @Pattern(regexp = "\\d{5}-\\d{3}|\\d{8}", message = "Zip code must be on format XXXXX-XXX or XXXXXXXX")
        String zipCode,

        @NotBlank(message = "Street can not be empty")
        String street,

        @NotBlank(message = "City can not be empty")
        String city,

        @NotBlank(message = "State can not be empty")
        @Size(max = 2, message = "State must have only 2 characters")
        String state
) {
}
