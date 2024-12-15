package com.ecommerce.walmart.controllers.resourceView;

import com.ecommerce.walmart.entities.Address;
import com.ecommerce.walmart.entities.Product;
import com.ecommerce.walmart.entities.Role;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Setter
@Getter
public class UserRequestRv {
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    private String prefix;
    @NotNull
    private String mobileNumber;
    @NotNull
    private String emailAddress;
    @NotNull
    private Role role;
}
