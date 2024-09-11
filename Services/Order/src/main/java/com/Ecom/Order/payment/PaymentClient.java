package com.Ecom.Order.payment;

import feign.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name = "payment-service",
        url="${application.config.payment-url}"
)
public interface PaymentClient {


    @PostMapping
    Response requestOrderPayment(@RequestBody PaymentRequest request);

}
