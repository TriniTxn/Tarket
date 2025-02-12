package br.com.devannis.webmarket.model.dto;

import br.com.devannis.webmarket.model.entity.User;

public record UserExhibitionDTO(
        Long userId,
        String username,
        String email
) {
    public UserExhibitionDTO(User user) {
        this(user.getUserId(), user.getUsername(), user.getEmail());
    }
}
