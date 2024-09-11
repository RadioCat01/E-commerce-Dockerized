package com.Ecom.Product.product;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService service;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Integer> createProduct(
            @RequestPart("request") @Valid ProductRequest request,
            @RequestPart("image") MultipartFile image

    ) throws IOException {
        return ResponseEntity.ok(service.createProduct(request, image));
    }

    @PostMapping("/purchase")
    public ResponseEntity<List<ProductPurchaseResponse>> purchaseProducts(
            @RequestBody List<ProductPurchaseRequest> requests
    ) {
        return ResponseEntity.ok(service.purchaseProducts(requests));
    }

}
