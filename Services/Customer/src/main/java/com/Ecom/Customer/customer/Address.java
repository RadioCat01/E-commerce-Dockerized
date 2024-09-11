package com.Ecom.Customer.customer;

import jakarta.persistence.Embeddable;
import lombok.*;
import org.springframework.validation.annotation.Validated;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Validated
@Embeddable
public class Address {

    private String street;
    private String houseNumber;
    private String zipCode;
}
