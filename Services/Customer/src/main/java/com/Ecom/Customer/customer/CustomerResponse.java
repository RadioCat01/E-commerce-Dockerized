package com.Ecom.Customer.customer;

public record CustomerResponse(
        String Id,
        String firstname,
        String lastname,
        String email,
        Address address
) {
}
