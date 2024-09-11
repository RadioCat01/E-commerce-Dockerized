package com.Ecom.Order.kafka;

import com.Ecom.Order.customer.CustomerResponse;
import com.Ecom.Order.order.PaymentMethod;
import com.Ecom.Order.product.PurchaseResponse;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation(
        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        CustomerResponse customer,
        List<PurchaseResponse> products
) {
}
