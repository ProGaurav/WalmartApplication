package com.ecommerce.walmart.controllers.resourceView;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AddressResponseRv {
    private int id;

    private String addressLine;

    private String state;

    private String city;

    private int pincode;
}
