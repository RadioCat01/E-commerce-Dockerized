package com.Ecom.Order.order;

import com.Ecom.Order.product.PurchaseRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.List;

public record OrderRequest(
        Integer id,
        String reference,

        @Positive(message = "Order amount should be positive")
        BigDecimal amount,

        @NotNull(message = "Select one")
        PaymentMethod paymentMethod,

        @NotEmpty(message = "Select a product")
        List<PurchaseRequest> products,

        @NotEmpty(message = "Street required")
        String street,
        @NotEmpty(message = "House Number required")
        String houseNumber,
        @NotEmpty(message = "Zip code required")
        String zipCode
) {
}
