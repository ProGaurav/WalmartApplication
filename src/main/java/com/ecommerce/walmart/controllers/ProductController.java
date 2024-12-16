package com.ecommerce.walmart.controllers;

import com.ecommerce.walmart.exceptions.ResourceNotFoundException;
import com.ecommerce.walmart.services.dtos.ProductRequestDto;
import com.ecommerce.walmart.services.dtos.ProductResponseDto;
import com.ecommerce.walmart.services.productService.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@Slf4j
public class ProductController {
    @Autowired
    private ProductService prodService;
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private ObjectMapper objectMapper;

    //Get Products
    @GetMapping("/products")
    public ResponseEntity<List<ProductResponseDto>> fetchAllProducts() throws ResourceNotFoundException {
        List<ProductResponseDto> products = prodService.fetchAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    //add
    @PostMapping("/products")
    public ResponseEntity<ProductResponseDto> addNewProduct(@Valid @RequestBody ProductRequestDto productRequestDto) throws ResourceNotFoundException {
        ProductResponseDto productResponseDto = prodService.addProduct(productRequestDto);
        return new ResponseEntity<>(productResponseDto, HttpStatus.CREATED);
    }

    //delete
    @DeleteMapping("/product/{productId}")
    public ResponseEntity<String> deleteProduct(@PathVariable("productId") int id) throws ResourceNotFoundException {
        prodService.deleteProduct(id);
        return new ResponseEntity<>("Product deleted successfully", HttpStatus.OK);
    }

    //update
    @PutMapping("/product/{productId}")
    public ResponseEntity<ProductResponseDto> updateProduct(@PathVariable("productId") int id, @RequestBody ProductRequestDto request) throws ResourceNotFoundException {
        ProductResponseDto updatedProduct = prodService.updateProduct(request, id);
        return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
    }

}
