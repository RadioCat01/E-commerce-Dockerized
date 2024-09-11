package com.Ecom.Customer.customer;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue
    private Integer userId;

    @Column(nullable = false, unique = true)
    private String id;
    private String firstname;
    private String lastname;
    private String email;
    @Embedded
    private Address address;
}
