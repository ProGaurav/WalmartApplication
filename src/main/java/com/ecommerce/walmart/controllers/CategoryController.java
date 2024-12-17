package com.ecommerce.walmart.controllers;

import com.ecommerce.walmart.entities.Category;
import com.ecommerce.walmart.exceptions.ResourceNotFoundException;
import com.ecommerce.walmart.services.categoryService.CategoryService;
import com.ecommerce.walmart.services.dtos.CategoryRequestDto;
import com.ecommerce.walmart.services.dtos.CategoryResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    //add
    @RequestMapping(name = "/category", method = RequestMethod.POST)
    public ResponseEntity<CategoryResponseDto> addCategory(@RequestBody CategoryRequestDto categoryRequestDto) {
       CategoryResponseDto categoryResponseDto = categoryService.addCategory(categoryRequestDto);
       return new ResponseEntity<>(categoryResponseDto, HttpStatus.OK);
    }
    //fetchAll
    @RequestMapping(value = "/categories",  method = RequestMethod.GET)
    public ResponseEntity<List<CategoryResponseDto>> fetchAllCategories() {
        List<CategoryResponseDto> categories = categoryService.fetchAllCategories();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }
    //delete
    @RequestMapping(value = "/category/{categoryId}")
    public ResponseEntity<String> deleteCategory(@PathVariable("categoryId") int id) throws ResourceNotFoundException {
        categoryService.deleteCategory(id);
        return new ResponseEntity<>("Category deleted succesfully!", HttpStatus.OK);
    }
    //update
    @RequestMapping(value = "/category/{categoryId}")
    public ResponseEntity<CategoryResponseDto> updateCategory(@PathVariable("categoryId")int id, @RequestBody CategoryRequestDto categoryRequestDto) throws ResourceNotFoundException {
        CategoryResponseDto categoryResponseDto = categoryService.updateCategory(categoryRequestDto, id);
        return new ResponseEntity<>(categoryResponseDto, HttpStatus.OK);
    }
}
