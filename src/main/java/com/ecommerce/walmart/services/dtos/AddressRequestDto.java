package com.ecommerce.walmart.services.dtos;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AddressRequestDto {

    private String addressLine;

    private String state;

    private String city;

    private int pincode;

    private int userId;
}
