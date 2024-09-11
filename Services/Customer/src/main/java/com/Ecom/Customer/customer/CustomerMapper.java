package com.Ecom.Customer.customer;

import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {
    public Customer toCustomer(CustomerRequest request) {

        if(request==null){
            return null;
        }

        return Customer.builder()
                .id(request.Id())
                .email(request.email())
                .firstname(request.firstname())
                .lastname(request.lastname())
                .address(request.address())
                .build();
    }

    public CustomerResponse fromCustomer(Customer customer) {
        return new CustomerResponse(
                customer.getId(),
                customer.getFirstname(),
                customer.getLastname(),
                customer.getEmail(),
                customer.getAddress()
        );
    }
}
