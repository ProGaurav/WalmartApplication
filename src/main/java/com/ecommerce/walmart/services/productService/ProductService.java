package com.ecommerce.walmart.services.productService;

import com.ecommerce.walmart.exceptions.ResourceNotFoundException;
import com.ecommerce.walmart.services.dtos.ProductRequestDto;
import com.ecommerce.walmart.services.dtos.ProductResponseDto;
import java.util.List;

public interface ProductService {
    List<ProductResponseDto> fetchAllProducts() throws ResourceNotFoundException;
    ProductResponseDto addProduct(ProductRequestDto productDto) throws ResourceNotFoundException;
    void deleteProduct(int productId) throws ResourceNotFoundException;
    ProductResponseDto updateProduct(ProductRequestDto productRequestDto, int productId) throws ResourceNotFoundException;
    ProductResponseDto fetchSingleProduct(int productId) throws ResourceNotFoundException;
}

