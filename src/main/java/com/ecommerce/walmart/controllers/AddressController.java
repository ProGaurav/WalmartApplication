package com.ecommerce.walmart.controllers;

import com.ecommerce.walmart.exceptions.ResourceNotFoundException;
import com.ecommerce.walmart.services.addressService.AddressService;
import com.ecommerce.walmart.services.dtos.AddressRequestDto;
import com.ecommerce.walmart.services.dtos.AddressResponseDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AddressController {
    @Autowired
    private AddressService addressService;

    @RequestMapping(path = "/address", method = RequestMethod.POST)
    public ResponseEntity<AddressResponseDto> addAddress(@Valid @RequestBody AddressRequestDto addressRequestDto) throws ResourceNotFoundException {
        AddressResponseDto address = addressService.addAddress(addressRequestDto);
        return new ResponseEntity<>(address, HttpStatus.OK);
    }

    @RequestMapping(path = "/address/{addressId}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteAddress(@PathVariable("addressId")int id) throws ResourceNotFoundException {
         addressService.deleteAddress(id);
         return new ResponseEntity<>("Address deleted successfully!", HttpStatus.OK);
    }

    @RequestMapping(value = "/address/user/{userId}}", method = RequestMethod.GET)
    public ResponseEntity<List<AddressResponseDto>> fetchAddress(@PathVariable("userId")int id) throws ResourceNotFoundException {
        List<AddressResponseDto> addressResponseDto = addressService.fetchAddress(id);
        return new ResponseEntity<>(addressResponseDto, HttpStatus.OK);
    }

    @RequestMapping(value = "/address/{addressId}", method = RequestMethod.PUT)
    public ResponseEntity<AddressResponseDto> updateAddress(@PathVariable("addressId")int id, @RequestBody AddressRequestDto addressRequestDto) throws ResourceNotFoundException {
        AddressResponseDto addressResponseDto = addressService.updateAddress(addressRequestDto, id);
        return new ResponseEntity<>(addressResponseDto, HttpStatus.ACCEPTED);
    }
}
