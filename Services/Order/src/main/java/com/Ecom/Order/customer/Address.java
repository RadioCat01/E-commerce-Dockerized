package com.Ecom.Order.customer;

import jakarta.persistence.Embeddable;
import lombok.*;
import org.springframework.validation.annotation.Validated;

public record Address (
        String street,
        String houseNumber,
        String zipCode
){ }
