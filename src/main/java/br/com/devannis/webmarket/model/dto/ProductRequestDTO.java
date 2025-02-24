package br.com.devannis.webmarket.model.dto;

import br.com.devannis.webmarket.model.enums.Category;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record ProductRequestDTO(

        Long productId,

        @NotBlank(message = "O nome do produto é obrigatório")
        String productName,

        String productDescription,

        @NotNull(message = "O preço do produto é obrigatório")
        @Min(value = 0, message = "O preço deve ser maior ou igual a zero")
        Double productPrice,

        @NotNull(message = "A quantidade em estoque é obrigatória")
        @Min(value = 0, message = "A quantidade em estoque não pode ser negativa")
        int stockQuantity,

        Category category
) {}
