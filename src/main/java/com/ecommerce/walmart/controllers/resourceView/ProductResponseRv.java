package com.ecommerce.walmart.controllers.resourceView;

import com.ecommerce.walmart.services.dtos.CategoryResponseDto;
import com.ecommerce.walmart.services.dtos.UserResponseDto;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Setter
@Getter
public class ProductResponseRv {
    private int id;
    private String name;
    private long price;
    private String description;
    private UserResponseDto user;
    private Date addedDate;
    private List<CategoryResponseDto> categories;
}
