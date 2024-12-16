package com.ecommerce.walmart.controllers;

import com.ecommerce.walmart.exceptions.InvalidUserException;
import com.ecommerce.walmart.exceptions.ResourceNotFoundException;
import com.ecommerce.walmart.services.dtos.UserRequestDto;
import com.ecommerce.walmart.services.dtos.UserResponseDto;
import com.ecommerce.walmart.services.userService.UserService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private UserService userService;

    @PostMapping("/users")
    public ResponseEntity<UserResponseDto> createUser(@Valid @RequestBody UserRequestDto userRequestDto) throws InvalidUserException {
        UserResponseDto userResponseDto = userService.addUser(userRequestDto);
        return new ResponseEntity<>(userResponseDto, HttpStatus.CREATED);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<UserResponseDto> fetchUser(@PathVariable("userId") int id) throws ResourceNotFoundException {
        UserResponseDto userResponseDto = userService.fetchSingleUser(id);
        return new ResponseEntity<>(userResponseDto, HttpStatus.OK);
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserResponseDto>> fetchAllUsers() {
        List<UserResponseDto> userResponseDtoList = userService.fetchAllUsers();
        return new ResponseEntity<>(userResponseDtoList, HttpStatus.OK);
    }

    @DeleteMapping("/user/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable("userId")int id) throws ResourceNotFoundException {
         userService.deleteUser(id);
         return new ResponseEntity<>("User with id: " + id + " deleted successfully!", HttpStatus.OK);
    }

    @PutMapping("user/{userId}")
    public ResponseEntity<UserResponseDto> updateUser(@PathVariable("userId")int id, @RequestBody UserRequestDto userRequestDto) throws ResourceNotFoundException {
       UserResponseDto userResponseDto = userService.updateUser(userRequestDto, id);
       return new ResponseEntity<>(userResponseDto, HttpStatus.OK);
    }

}
