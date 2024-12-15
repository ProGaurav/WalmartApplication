package com.ecommerce.walmart.services.dtos;

import com.ecommerce.walmart.entities.Role;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Setter
@Getter
public class UserRequestDto {
    private String firstName;
    private String lastName;
    private String prefix;
    private String mobileNumber;
    private String emailAddress;
    private Role role;
}
