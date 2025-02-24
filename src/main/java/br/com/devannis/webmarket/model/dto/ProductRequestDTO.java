package br.com.devannis.webmarket.model.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProductRequestDTO(

        @NotBlank(message = "O nome do produto é obrigatório")
        String name,

        String description,

        String imageUrl,

        @NotNull(message = "O preço do produto é obrigatório")
        @Min(value = 0, message = "O preço deve ser maior ou igual a zero")
        Double price,

        @NotNull(message = "A quantidade em estoque é obrigatória")
        @Min(value = 0, message = "A quantidade em estoque não pode ser negativa")
        Integer stockQuantity,

        @NotNull(message = "O peso do produto é obrigatório")
        @Min(value = 0, message = "O peso deve ser maior ou igual a zero")
        Double weight,

        @NotNull(message = "A categoria do produto é obrigatória")
        Long categoryId
) {}
