package com.Ecom.Order.customer;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@FeignClient(
        name = "customerService",
        url = "${application.config.customer-url}"
)
public interface CustomerClient {

    @PostMapping
    String createCustomer( @RequestBody CustomerRequest customer );

}
