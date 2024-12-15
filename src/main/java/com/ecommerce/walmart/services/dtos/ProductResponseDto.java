package com.ecommerce.walmart.services.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Setter
@Getter
public class ProductResponseDto {
    private int id;
    private String name;
    private long price;
    private String description;
    private UserResponseDto user;
    private Date addedDate;
    private List<CategoryResponseDto> categories;
}
