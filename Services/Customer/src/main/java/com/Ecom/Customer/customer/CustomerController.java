package com.Ecom.Customer.customer;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService service;

    @PostMapping
    public ResponseEntity<String> createCustomer(
            @RequestBody @Valid CustomerRequest request
    ) {
        return ResponseEntity.ok(service.createCustomer(request));
    }

    @PutMapping
    public ResponseEntity<Void> updateCustomer(
            @RequestBody @Valid CustomerRequest request
    ){
        service.updateCustomer(request);
        return  ResponseEntity.accepted().build();
    }

    @GetMapping
    public ResponseEntity<List<CustomerResponse>> findAll(){
        return ResponseEntity.ok(service.findAllCustomers());
    }

    @GetMapping("/exits/{customer-id}")
    public ResponseEntity<Boolean> existsById(
            @PathVariable("customer-id") String id
    ){
        return ResponseEntity.ok(service.existsById(id));
    }

    @GetMapping("/{customer-id}")
    public ResponseEntity<CustomerResponse> findById(
            @PathVariable("customer-id") String id
    ){
        return ResponseEntity.ok(service.findById(id));
    }

    @DeleteMapping("/{customer-id}")
    public ResponseEntity<Void> delete(
            @PathVariable("customer-id") String id
    ){
        service.deleteCustomer(id);
        return ResponseEntity.accepted().build();
    }
}
