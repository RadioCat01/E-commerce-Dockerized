package com.Ecom.Customer.customer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record CustomerRequest(
         String Id,

         @NotNull(message = "first name is required")
         String firstname,

         @NotNull(message = "last name is required")
         String lastname,

         @NotNull(message = "Email is required")
         @Email(message = "Email not valid")
         String email,

         @NotNull(message = "first name is required")
         Address address

) {
}
