package com.Ecom.Order.product;


import com.Ecom.Order.exception.BusinesException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

//RestTemplate

@Service
@RequiredArgsConstructor
public class ProductClient {

    @Value("${application.config.product-url}")
    private String productUrl;

    private final RestTemplate restTemplate;

    public List<PurchaseResponse> purchaseProducts(List<PurchaseRequest> requestsBody){
        HttpHeaders headers = new org.springframework.http.HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

        HttpEntity<List<PurchaseRequest>> requestEntity = new HttpEntity<>(requestsBody, headers);

        ParameterizedTypeReference<List<PurchaseResponse>> responseType = new ParameterizedTypeReference<List<PurchaseResponse>>(){};

        ResponseEntity<List<PurchaseResponse>> responseEntity = restTemplate.exchange(
                productUrl + "/purchase", HttpMethod.POST,requestEntity,responseType
        );

        if(responseEntity.getStatusCode().isError()){
            throw new BusinesException("Error while processing the purchase ");
        }
        return responseEntity.getBody();
    }

}
