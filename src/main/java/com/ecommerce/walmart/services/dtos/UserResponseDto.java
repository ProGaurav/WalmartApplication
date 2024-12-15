package com.ecommerce.walmart.services.dtos;

import com.ecommerce.walmart.entities.Role;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Setter
@Getter
public class UserResponseDto {
    private int id;
    private String firstName;
    private String lastName;
    private String prefix;
    private String mobileNumber;
    private String emailAddress;
    private Role role;
    private Date registeredDate;
    private List<AddressResponseDto> addresses;
}
