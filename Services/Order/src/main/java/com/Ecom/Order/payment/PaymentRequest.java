package com.Ecom.Order.payment;


import com.Ecom.Order.customer.CustomerResponse;
import com.Ecom.Order.order.PaymentMethod;

import java.math.BigDecimal;

public record PaymentRequest(
        BigDecimal amount,
        PaymentMethod paymentMethod,
        Integer orderId,
        String orderReference,
        CustomerResponse customer
) {
}
