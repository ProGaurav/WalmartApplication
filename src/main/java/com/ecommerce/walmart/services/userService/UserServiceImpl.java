package com.ecommerce.walmart.services.userService;

import com.ecommerce.walmart.entities.User;
import com.ecommerce.walmart.exceptions.InvalidUserException;
import com.ecommerce.walmart.exceptions.ResourceNotFoundException;
import com.ecommerce.walmart.repositories.UserRepository;
import com.ecommerce.walmart.services.dtos.AddressResponseDto;
import com.ecommerce.walmart.services.dtos.UserRequestDto;
import com.ecommerce.walmart.services.dtos.UserResponseDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private ModelMapper mapper;

    @Override
    public List<UserResponseDto> fetchAllUsers() {
        List<User> users = userRepo.findAll();
        List<UserResponseDto> userResponseDtoList = users.stream().map(user -> {
            UserResponseDto userResponseDto = mapper.map(user, UserResponseDto.class);
            List<AddressResponseDto> addressDtoList = user.getAddresses().stream().map(address -> mapper.map(address, AddressResponseDto.class)).collect(Collectors.toList());
            userResponseDto.setAddresses(addressDtoList);
            return userResponseDto;
        }).collect(Collectors.toList());
        return userResponseDtoList;
    }

    @Override
    public UserResponseDto addUser(UserRequestDto userRequestDto) throws InvalidUserException {
        if (!userRequestDto.getEmailAddress().isEmpty()) {
            User user = userRepo.findByEmailAddress(userRequestDto.getEmailAddress());
            if (user != null) {
                throw new InvalidUserException("Email address already exists in database!");
            }
        }

       if (!userRequestDto.getMobileNumber().isEmpty()) {
           User user = userRepo.findByMobileNumber(userRequestDto.getMobileNumber());
           if (user != null) {
               throw new InvalidUserException("Mobile number already exists in database!");
           }
       }

        User newUser = mapper.map(userRequestDto, User.class);
        newUser.setRegisteredDate(Date.from(Instant.now()));
        User savedUser = userRepo.save(newUser);
        return mapper.map(savedUser, UserResponseDto.class);
    }

    @Override
    public void deleteUser(int userId) throws ResourceNotFoundException {
        User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User with id: " + userId + "not found in database!"));
        userRepo.delete(user);
    }

    @Override
    public UserResponseDto updateUser(UserRequestDto userRequestDto, int userId) throws ResourceNotFoundException {
        User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User with id: " + userId + "not found in database!"));
        user.setPrefix(userRequestDto.getPrefix());
        user.setRole(userRequestDto.getRole());
        user.setFirstName(userRequestDto.getFirstName());
        user.setLastName(userRequestDto.getLastName());
        user.setEmailAddress(userRequestDto.getEmailAddress());
        user.setMobileNumber(userRequestDto.getMobileNumber());
        User savedUser = userRepo.save(user);
        return mapper.map(savedUser, UserResponseDto.class);
    }

    @Override
    public UserResponseDto fetchSingleUser(int userId) throws ResourceNotFoundException {
        User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User with id: " + userId + "not found in database!"));
        UserResponseDto userResponseDto = mapper.map(user, UserResponseDto.class);
        List<AddressResponseDto> addressDtoList = user.getAddresses().stream().map(address -> mapper.map(address, AddressResponseDto.class)).collect(Collectors.toList());
        userResponseDto.setAddresses(addressDtoList);
        return userResponseDto;
    }
}
