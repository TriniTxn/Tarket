package br.com.devannis.webmarket.model.dto;

import br.com.devannis.webmarket.model.entity.Product;
import br.com.devannis.webmarket.model.enums.Category;

public record ProductResponseDTO(
        Long productId,
        String name,
        String description,
        Double price,
        Integer stockQuantity,
        Category category
) {
    public ProductResponseDTO(Product product) {
        this (
            product.getProductId(),
                product.getProductName(),
                product.getProductDescription(),
                product.getProductPrice(),
                product.getStockQuantity(),
                product.getCategory()
        );
    }
}