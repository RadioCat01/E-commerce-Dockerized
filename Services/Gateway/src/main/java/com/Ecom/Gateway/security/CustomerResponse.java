package com.Ecom.Gateway.security;

public record CustomerResponse(
        String id,
        String firstname,
        String lastname,
        String email
) {
}
