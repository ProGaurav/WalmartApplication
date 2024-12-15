package com.ecommerce.walmart.controllers.resourceView;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CategoryRequestRv {
    @NotNull
    private String name;
    @NotNull
    private String Description;
}
