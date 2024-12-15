package com.ecommerce.walmart.services.userService;

import com.ecommerce.walmart.exceptions.ResourceNotFoundException;
import com.ecommerce.walmart.services.dtos.AddressRequestDto;
import com.ecommerce.walmart.services.dtos.UserRequestDto;
import com.ecommerce.walmart.services.dtos.UserResponseDto;

import java.util.List;

public interface UserService {
    //Get
    List<UserResponseDto> fetchAllUsers();
    //Post
   UserResponseDto addUser(UserRequestDto user);
    //Delete
    void deleteUser(int userId) throws ResourceNotFoundException;
    //Put
    UserResponseDto updateUser(UserRequestDto user, int userId) throws ResourceNotFoundException;

    UserResponseDto fetchSingleUser(int userId) throws ResourceNotFoundException;
}
