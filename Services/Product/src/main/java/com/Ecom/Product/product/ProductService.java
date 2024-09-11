package com.Ecom.Product.product;

import com.Ecom.Product.exception.productPurchaseException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repository;
    private final ProductMapper mapper;
    private final String uploadDir = "uploads/";

    public Integer createProduct(ProductRequest request, MultipartFile image) throws IOException {

        if (!image.isEmpty()) {
            String fileName = image.getOriginalFilename();
            Path filePath = Paths.get(uploadDir, fileName);
            Files.createDirectories(filePath.getParent());
            Files.write(filePath, image.getBytes());
            var product = mapper.toProduct(request, filePath.toString());
            return  repository.save(product).getId();
        }
        return null;
    }

    public List<ProductPurchaseResponse> purchaseProducts(List<ProductPurchaseRequest> requests) {
         var productIds = requests.stream()
                 .map(ProductPurchaseRequest::productId).toList();

         var storedProducts = repository.findAllByIdInOrderById(productIds);

         if(productIds.size() != storedProducts.size()){
             throw new productPurchaseException(" One or more products not found ");
         }

         var storesRequest = requests.stream().sorted(Comparator.comparing(ProductPurchaseRequest::productId)).toList();

         var purchasedProducts = new ArrayList<ProductPurchaseResponse>();

         for(int i=0; i< storedProducts.size(); i++){
             var product = storedProducts.get(i);
             var productRequest = storesRequest.get(i);

             if(product.getAvailableQuantity() < productRequest.quantity()){
                 throw new productPurchaseException("Out of Stock" + productRequest.productId());
             }

             var newAwailableQuantity = product.getAvailableQuantity()-productRequest.quantity();
             product.setAvailableQuantity(newAwailableQuantity);
             repository.save(product);
             purchasedProducts.add(mapper.toProductPurchaseResponse(product, productRequest.quantity()));
         }
         return purchasedProducts;
    }

    public ProductResponse findById(Integer id) {
        return repository.findById(id).map(mapper::toProductResponse)
                .orElseThrow(()-> new EntityNotFoundException("Product not found"));
    }

    public List<ProductResponse> findAll() {
        return repository.findAll().stream().map(mapper::toProductResponse).collect(Collectors.toList());
    }

    public List<ProductResponse> findAllMen() {
        return repository.findAllMen().stream().map(mapper::toProductResponse).collect(Collectors.toList());
    }
    public List<ProductResponse> findAllWomen() {
        return repository.findAllWomen().stream().map(mapper::toProductResponse).collect(Collectors.toList());
    }

    public List<ProductResponse> findAllUnisex() {
        return repository.findAllUnisex().stream().map(mapper::toProductResponse).collect(Collectors.toList());
    }

}
