package br.com.devannis.webmarket.model.dto;

import br.com.devannis.webmarket.model.entity.User;
import br.com.devannis.webmarket.model.enums.UserRole;

public record UserResponseDTO(
        Long userId,
        String username,
        String email,
        UserRole role
) {
    public UserResponseDTO(User user) {
        this(user.getUserId(), user.getUsername(), user.getEmail(), user.getRole());
    }
}
