package com.Ecom.Product.exception;

import java.util.Map;

public record ErrorResponse(
        Map<String,String> errors
) {
}
