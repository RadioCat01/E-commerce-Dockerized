package com.Ecom.Customer.exception;

import jakarta.validation.constraints.Max;

import java.util.Map;

public record ErrorResponse(
        Map<String,String> errors
) {
}
