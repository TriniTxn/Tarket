package br.com.devannis.webmarket.model.dto;

import br.com.devannis.webmarket.model.enums.Category;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ProductRequestDTO(
        @NotBlank(message = "Product name must be inserted")
        @Size(max = 200, message = "Product name must have only 200 characters")
        String productName,

        @Size(max = 1000, message = "The description must have only 1000 characters")
        String productDescription,

        @NotNull(message = "You must insert the product price")
        @DecimalMin(value = "0.0", inclusive = false, message = "The price must be greater than 0")
        BigDecimal productPrice,

        @NotNull(message = "Stock quantity must be informed")
        @Min(value = 0, message = "Stock quantity can not be negative")
        int stockQuantity,

        @NotNull(message = "Category must be inserted")
        Category category
) {}
