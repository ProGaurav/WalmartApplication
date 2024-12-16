package com.ecommerce.walmart.services.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AddressRequestDto {
    @NotNull
    private String addressLine;
    @NotNull
    private String state;
    @NotNull
    private String city;
    @NotNull
    private int pincode;
    @NotNull
    private int userId;
}
