package com.ecommerce.walmart.controllers.resourceView;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AddressRequestRv {
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
