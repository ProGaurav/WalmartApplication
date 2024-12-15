package com.ecommerce.walmart.services.categoryService;

import com.ecommerce.walmart.entities.Category;
import com.ecommerce.walmart.exceptions.ResourceNotFoundException;
import com.ecommerce.walmart.repositories.CategoryRepository;
import com.ecommerce.walmart.services.dtos.CategoryResponseDto;
import com.ecommerce.walmart.services.dtos.CategoryRequestDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService{
    @Autowired
    private CategoryRepository categoryRepo;
    @Autowired
    private ModelMapper mapper;
    @Override
    public List<CategoryResponseDto> fetchAllCategories() {
        List<Category> categories  = categoryRepo.findAll();
        List<CategoryResponseDto> categoryDtoList = categories.stream().map(category -> mapper.map(category, CategoryResponseDto.class)).collect(Collectors.toList());
        return categoryDtoList;
    }

    @Override
    public CategoryResponseDto fetchSingleCategory(int catId) throws ResourceNotFoundException {
       Category category = categoryRepo.findById(catId).orElseThrow(() -> new ResourceNotFoundException("Category with id: " + catId + " not found in database!"));
       return mapper.map(category, CategoryResponseDto.class);
    }

    @Override
    public CategoryResponseDto addCategory(CategoryRequestDto categoryRequestDto) {
        Category category = mapper.map(categoryRequestDto, Category.class);
        Category savedCategory = categoryRepo.save(category);
        return mapper.map(savedCategory, CategoryResponseDto.class);
    }

    @Override
    public CategoryResponseDto updateCategory(CategoryRequestDto categoryRequestDto, int catId) throws ResourceNotFoundException {
        Category category = categoryRepo.findById(catId).orElseThrow(() -> new ResourceNotFoundException("Category with id: " + catId + " not found in database!"));
        category.setName(categoryRequestDto.getName());
        category.setDescription(categoryRequestDto.getDescription());
        Category savedCategory = categoryRepo.save(category);
        return mapper.map(savedCategory, CategoryResponseDto.class);
    }

    @Override
    public void deleteCategory(int categoryId) throws ResourceNotFoundException {
       Category category = categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category with id: " + categoryId + " not found in database!"));
       categoryRepo.delete(category);
    }
}
