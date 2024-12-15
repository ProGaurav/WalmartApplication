package com.ecommerce.walmart.services.dtos;

import com.ecommerce.walmart.entities.Status;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Setter
@Getter
public class OrderDto {
    private ProductRequestDto product;
    private UserResponseDto user;
    private AddressResponseDto address;
    private int quantity;
    private Date orderedDate;
    private long price;
    private Status status;
}
