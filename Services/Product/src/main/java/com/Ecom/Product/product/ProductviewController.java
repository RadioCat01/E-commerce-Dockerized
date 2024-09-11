package com.Ecom.Product.product;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/viewProducts")
@RequiredArgsConstructor
public class ProductviewController {

    private final ProductService service;


    @GetMapping("/{product-id}")
    public ResponseEntity<ProductResponse> findById(
            @PathVariable("product-id") Integer id
    ){
        return ResponseEntity.ok(service.findById(id));
    }
    @GetMapping
    public ResponseEntity<List<ProductResponse>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/Men")
    public ResponseEntity<List<ProductResponse>> findMen(){
        return ResponseEntity.ok(service.findAllMen());
    }


    @GetMapping("/Women")
    public ResponseEntity<List<ProductResponse>> findWomen(){
        return ResponseEntity.ok(service.findAllWomen());
    }

    @GetMapping("/Unisex")
    public ResponseEntity<List<ProductResponse>> findUnisex(){
        return ResponseEntity.ok(service.findAllUnisex());
    }

}
