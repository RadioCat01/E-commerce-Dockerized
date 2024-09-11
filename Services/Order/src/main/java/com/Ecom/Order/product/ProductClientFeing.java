package com.Ecom.Order.product;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(
        name = "product-service",
        url = "${application.config.product-url}"
)
public interface ProductClientFeing {

    @PostMapping(value = "/purchase")
    List<PurchaseResponse> purchaseProducts(@RequestBody List<PurchaseRequest> requestsBody);

}
