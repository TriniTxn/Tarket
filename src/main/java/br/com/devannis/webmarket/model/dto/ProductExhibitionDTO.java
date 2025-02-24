package br.com.devannis.webmarket.model.dto;

public record ProductExhibitionDTO(
        Long id,
        String name,
        String description,
        String imageUrl,
        Double price,
        Integer stockQuantity,
        Double weight,
        String categoryName
) {}