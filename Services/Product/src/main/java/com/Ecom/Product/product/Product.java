package com.Ecom.Product.product;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class Product {

    @Id
    @GeneratedValue
    private Integer id;
    private String naame;
    private String description;
    private double availableQuantity;
    private BigDecimal price;

    private String imagePath;
    private String category;

}
