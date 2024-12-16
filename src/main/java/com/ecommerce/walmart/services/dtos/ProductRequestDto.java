package com.ecommerce.walmart.services.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Setter
@Getter
public class ProductRequestDto {
    @NotNull
    private String name;
    @NotNull
    private long price;
    @NotNull
    private String description;
    private int userId;
    private List<Integer> categoryIds;
}
