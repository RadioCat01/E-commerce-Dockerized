package com.Ecom.Product.product;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

@Component
public class ProductMapper {
    public Product toProduct(ProductRequest request , String path) {
        return Product.builder()
                .naame(request.naame())
                .description(request.description())
                .availableQuantity(request.availableQuantity())
                .price(request.price())
                .imagePath(path)
                .category(request.category())
                .build();
    }

    public ProductResponse toProductResponse(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getNaame(),
                product.getDescription(),
                product.getAvailableQuantity(),
                product.getPrice(),
                encodeImageToBase64(product.getImagePath()),
                product.getCategory()
        );
    }


    public ProductPurchaseResponse toProductPurchaseResponse(Product product, double quantity) {
        return new ProductPurchaseResponse(
                product.getId(),
                product.getNaame(),
                product.getDescription(),
                product.getPrice(),
                quantity
                );
    }

    private String encodeImageToBase64(String imagePath) {
        try {
            Path filePath = Paths.get(imagePath);
            byte[] imageBytes = Files.readAllBytes(filePath);
            return Base64.getEncoder().encodeToString(imageBytes);
        } catch (IOException e) {
            throw new RuntimeException("Could not read the file!", e);
        }
    }

}
