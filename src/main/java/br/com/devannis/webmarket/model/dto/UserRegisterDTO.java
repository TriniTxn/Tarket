package br.com.devannis.webmarket.model.dto;

import jakarta.validation.constraints.*;

public record UserRegisterDTO(
        Long userId,

        @NotBlank(message = "Username can not be empty")
        String username,

        @NotBlank(message = "Email can not be empty")
        @Email(message = "Email must be valid")
        String email,

        @NotBlank(message = "Password can not be empty")
        @Size(min = 5, message = "Password must have at least 5 characters")
        String password
) {
}
