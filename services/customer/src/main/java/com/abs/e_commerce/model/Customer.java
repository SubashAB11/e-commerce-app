package com.abs.e_commerce.model;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class Customer {

    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    @Embedded
    private Address address;

}
