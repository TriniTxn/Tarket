package br.com.devannis.webmarket.model.dto;

import br.com.devannis.webmarket.model.entity.User;

public record UserResponseDTO(
        Long userId,
        String username,
        String email
) {
    public UserResponseDTO(User user) {
        this(user.getUserId(), user.getUsername(), user.getEmail());
    }
}
