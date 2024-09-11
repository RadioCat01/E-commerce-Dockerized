package com.Ecom.Product.product;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ProductResponse(

        Integer id,
        String naame,
        String description,
        double availableQuantity,
        BigDecimal price,
        String imagePath,
        String category
) {
}
