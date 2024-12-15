package com.ecommerce.walmart.controllers.resourceView;

import com.ecommerce.walmart.entities.Address;
import com.ecommerce.walmart.entities.Product;
import com.ecommerce.walmart.entities.Status;
import com.ecommerce.walmart.entities.User;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class OrderRv {
    private int userId;
    private int productId;
    private Address address;
    private int quantity;
    private Date orderedDate;
    private long unitPrice;
    private long totalPrice;
    private Status status;
}
