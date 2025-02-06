package br.com.devannis.webmarket.model.dto;

import br.com.devannis.webmarket.model.entity.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserRegistrationDTO(
        Long userId,

        @NotBlank(message = "You must fill your name")
        String username,

        @Email(message = "Email must be valid")
        @NotBlank(message = "You must fill your email")
        String email,

        @Size(min = 6, message = "Your password contain at least 8 characters")
        @NotBlank(message = "You must fill with a password")
        String password,

        UserRole role
) {
}
