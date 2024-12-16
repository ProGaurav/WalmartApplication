package com.ecommerce.walmart.services.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss", timezone = "Asia/Kolkata")
    private Date addedDate;
    private List<CategoryResponseDto> categories;
}
