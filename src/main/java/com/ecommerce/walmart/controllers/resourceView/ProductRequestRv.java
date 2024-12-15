package com.ecommerce.walmart.controllers.resourceView;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Setter
@Getter
public class ProductRequestRv {
    @NotNull
    private String name;
    @NotNull
    private long price;
    @NotNull
    private String description;

    private int userId;
    @NotNull
    private Date addedDate;
    private List<Integer> categoryIds;
}
