package com.Ecom.Order.exception;

import java.util.Map;

public record ErrorResponse(
        Map<String,String> errors
) {
}
