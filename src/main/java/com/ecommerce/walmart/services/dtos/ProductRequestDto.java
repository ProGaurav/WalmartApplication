package com.ecommerce.walmart.services.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Setter
@Getter
public class ProductRequestDto {
    private String name;
    private long price;
    private String description;
    private int userId;
    private Date addedDate;
    private List<Integer> categoryIds;
}
