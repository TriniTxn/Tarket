package br.com.devannis.webmarket.model.dto;

import br.com.devannis.webmarket.model.entity.Product;
import br.com.devannis.webmarket.model.enums.Category;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ProductResponseDTO(
        Long productId,
        String name,
        String description,
        BigDecimal price,
        Integer stockQuantity,
        Category category,
        LocalDateTime addedAt,
        LocalDateTime updatedAt
) {
    public ProductResponseDTO(Product product) {
        this (
            product.getProductId(),
                product.getProductName(),
                product.getProductDescription(),
                product.getProductPrice(),
                product.getStockQuantity(),
                product.getCategory(),
                product.getAddedAt(),
                product.getUpdatedAt()
        );
    }
}