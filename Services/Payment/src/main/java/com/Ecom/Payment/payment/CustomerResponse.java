package com.Ecom.Payment.payment;


public record CustomerResponse(
        String id,
        String firstName,
        String lastName,
        String email
) {
}
