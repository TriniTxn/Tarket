package br.com.devannis.webmarket.model.dto;

import br.com.devannis.webmarket.model.enums.UserRole;
import jakarta.validation.constraints.*;

public record UserRequestDTO(
        @NotBlank(message = "Username can not be empty")
        String username,

        @NotBlank(message = "Email can not be empty")
        @Email(message = "Email must be valid")
        String email,

        @NotBlank(message = "Password can not be empty")
        @Size(min = 5, message = "Password must have at least 5 characters")
        String password,

        @NotNull(message = "Role can not be null")
        UserRole role
) {
}
