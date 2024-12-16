package com.ecommerce.walmart.controllers;

import com.ecommerce.walmart.controllers.resourceView.UserRequestRv;
import com.ecommerce.walmart.controllers.resourceView.UserResponseRv;
import com.ecommerce.walmart.exceptions.InvalidUserException;
import com.ecommerce.walmart.services.dtos.UserRequestDto;
import com.ecommerce.walmart.services.dtos.UserResponseDto;
import com.ecommerce.walmart.services.userService.UserService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private UserService userService;

    //add user
    @PostMapping("/users")
    public ResponseEntity<UserResponseRv> createUser(@Valid @RequestBody UserRequestRv userRequestRv) throws InvalidUserException {
        UserResponseDto userResponseDto = userService.addUser(mapper.map(userRequestRv, UserRequestDto.class));
        return new ResponseEntity<>(mapper.map(userResponseDto, UserResponseRv.class), HttpStatus.CREATED);
    }
    //fetch single
    //fetch all
    //delete
    //update
}
