package com.ecommerce.walmart.services.categoryService;

import com.ecommerce.walmart.exceptions.ResourceNotFoundException;
import com.ecommerce.walmart.services.dtos.CategoryResponseDto;
import com.ecommerce.walmart.services.dtos.CategoryRequestDto;

import java.util.List;

public interface CategoryService {
    List<CategoryResponseDto> fetchAllCategories();

    CategoryResponseDto fetchSingleCategory(int catId) throws ResourceNotFoundException;

    CategoryResponseDto addCategory(CategoryRequestDto categoryRequestDto);

    CategoryResponseDto updateCategory(CategoryRequestDto categoryRequestDto, int catId) throws ResourceNotFoundException;

    void deleteCategory(int categoryId) throws ResourceNotFoundException;
}
