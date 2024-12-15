package com.ecommerce.walmart.services.addressService;

import com.ecommerce.walmart.exceptions.ResourceNotFoundException;
import com.ecommerce.walmart.services.dtos.AddressRequestDto;
import com.ecommerce.walmart.services.dtos.AddressResponseDto;

import java.util.List;

public interface AddressService {
    List<AddressResponseDto> fetchAddress(int userId) throws ResourceNotFoundException;

    AddressResponseDto addAddress(AddressRequestDto addressRequestDto) throws ResourceNotFoundException;

    AddressResponseDto updateAddress(AddressRequestDto addressRequestDto, int addressId) throws ResourceNotFoundException;

    void deleteAddress(int addressId) throws ResourceNotFoundException;
}
