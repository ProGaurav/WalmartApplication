package com.ecommerce.walmart.services.addressService;

import com.ecommerce.walmart.entities.Address;
import com.ecommerce.walmart.entities.User;
import com.ecommerce.walmart.exceptions.ResourceNotFoundException;
import com.ecommerce.walmart.repositories.AddressRepository;
import com.ecommerce.walmart.repositories.UserRepository;
import com.ecommerce.walmart.services.dtos.AddressRequestDto;
import com.ecommerce.walmart.services.dtos.AddressResponseDto;
import com.ecommerce.walmart.services.dtos.UserResponseDto;
import com.ecommerce.walmart.services.userService.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
    private UserService userService;
    @Autowired
    private AddressRepository addressRepo;
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private UserRepository userRepo;

    @Override
    public List<AddressResponseDto> fetchAddress(int userId) throws ResourceNotFoundException {
        UserResponseDto user = userService.fetchSingleUser(userId);
        if (!user.getAddresses().isEmpty()) {
            return user.getAddresses();
        }
        throw new ResourceNotFoundException("Address not found! Please add your address");
    }

    @Override
    public AddressResponseDto addAddress(AddressRequestDto addressRequestDto) throws ResourceNotFoundException {
        Address address = mapper.map(addressRequestDto, Address.class);
        if (addressRequestDto.getUserId() <= 0) {
            throw new ResourceNotFoundException("Invalid User ID provided: " + addressRequestDto.getUserId());
        }
        if (address != null) {
            if (addressRequestDto.getUserId() > 0) {
                User user = userRepo.findById(addressRequestDto.getUserId()).orElseThrow(() -> new ResourceNotFoundException("User with id: " + addressRequestDto.getUserId() + "not found in database!"));
                user.getAddresses().add(address);
                userRepo.save(user);
                return mapper.map(address, AddressResponseDto.class);
            }
        }
        return null;
    }

    @Override
    public AddressResponseDto updateAddress(AddressRequestDto addressRequestDto, int addressId) throws ResourceNotFoundException {
        Address address = addressRepo.findById(addressId).orElseThrow(() -> new ResourceNotFoundException("Address not found in database!"));
        address.setAddressLine(addressRequestDto.getAddressLine());
        address.setCity(addressRequestDto.getCity());
        address.setState(addressRequestDto.getState());
        address.setPincode(addressRequestDto.getPincode());
        Address savedAddress = addressRepo.save(address);
        return mapper.map(savedAddress, AddressResponseDto.class);
    }

    @Override
    public void deleteAddress(int addressId) throws ResourceNotFoundException {
        Address address = addressRepo.findById(addressId).orElseThrow(() -> new ResourceNotFoundException("Address not found in database!"));
        addressRepo.delete(address);
    }
}
