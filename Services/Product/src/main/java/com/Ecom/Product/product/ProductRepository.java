package com.Ecom.Product.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Integer> {


    List<Product> findAllByIdInOrderById(List<Integer> productIds);

    @Query("SELECT p FROM Product p WHERE p.category LIKE 'Men%'" +
            "UNION ALL SELECT p FROM Product p WHERE p.category LIKE 'Unisex%'" )
    List<Product> findAllMen();

    @Query("SELECT p FROM Product p WHERE p.category LIKE 'Women%'" +
            "UNION ALL SELECT p FROM Product p WHERE p.category LIKE 'Unisex%'")
    List<Product> findAllWomen();

    @Query("SELECT p FROM Product p WHERE p.category LIKE 'Unisex%'")
    List<Product> findAllUnisex();
}
