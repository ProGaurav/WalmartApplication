package com.ecommerce.walmart.services.productService;

import com.ecommerce.walmart.entities.Category;
import com.ecommerce.walmart.entities.Product;
import com.ecommerce.walmart.entities.User;
import com.ecommerce.walmart.exceptions.ResourceNotFoundException;
import com.ecommerce.walmart.repositories.ProductRepository;
import com.ecommerce.walmart.services.categoryService.CategoryService;
import com.ecommerce.walmart.services.dtos.CategoryResponseDto;
import com.ecommerce.walmart.services.dtos.ProductRequestDto;
import com.ecommerce.walmart.services.dtos.ProductResponseDto;
import com.ecommerce.walmart.services.dtos.UserResponseDto;
import com.ecommerce.walmart.services.userService.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private ProductRepository prodRepo;
    @Autowired
    private UserService userService;
    @Autowired
    private CategoryService categoryService;

    @Override
    public List<ProductResponseDto> fetchAllProducts() throws ResourceNotFoundException {
        List<Product> products = prodRepo.findAll();
        if (products.isEmpty()) {
            throw new ResourceNotFoundException("products not found in database!");
        }

        return products.stream().map(product -> {
            ProductResponseDto productDto = mapper.map(product, ProductResponseDto.class);

            UserResponseDto userDto = mapper.map(product.getUser(), UserResponseDto.class);
            productDto.setUser(userDto);

            List<CategoryResponseDto> categoryDtos = product.getCategories().stream().map(category -> mapper.map(category, CategoryResponseDto.class)).collect(Collectors.toList());
            productDto.setCategories(categoryDtos);
            return productDto;
        }).collect(Collectors.toList());
    }

    @Override
    public ProductResponseDto addProduct(ProductRequestDto productDto) throws ResourceNotFoundException {
        Product product = mapper.map(productDto, Product.class);
        if (productDto.getUserId() > 0) {
            UserResponseDto userResponseDto = userService.fetchSingleUser(productDto.getUserId());
            User user = mapper.map(userResponseDto, User.class);
            product.setUser(user);
        }
        if (!productDto.getCategoryIds().isEmpty()) {
            List<Category> categories = productDto.getCategoryIds().stream().map(id -> {
                try {
                    CategoryResponseDto categoryDto = categoryService.fetchSingleCategory(id);
                    Category category = mapper.map(categoryDto, Category.class);
                    return category;
                } catch (ResourceNotFoundException e) {
                    throw new RuntimeException("Category with id: " + id + " not found!");
                }
            }).collect(Collectors.toList());
            product.setCategories(categories);
        }
        product.setAddedDate(Date.from(Instant.now()));
        Product savedProduct = prodRepo.save(product);
        return mapper.map(savedProduct, ProductResponseDto.class);
    }

    @Override
    public void deleteProduct(int productId) throws ResourceNotFoundException {
        Product product = prodRepo.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product with id: " + productId + "not found in database!"));
        prodRepo.delete(product);
    }

    @Override
    public ProductResponseDto updateProduct(ProductRequestDto productRequestDto, int productId) throws ResourceNotFoundException {
        Product product = prodRepo.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product with id: " + productId + "not found in database!"));
        product.setName(productRequestDto.getName());
        product.setPrice(productRequestDto.getPrice());
        product.setDescription(productRequestDto.getDescription());
        product.setAddedDate(Date.from(Instant.now()));

        Product savedProduct = prodRepo.save(product);
        return mapper.map(savedProduct, ProductResponseDto.class);
    }

    @Override
    public ProductResponseDto fetchSingleProduct(int productId) throws ResourceNotFoundException {
        Product product = prodRepo.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product with id: " + productId + "not found in database!"));
        return mapper.map(product, ProductResponseDto.class);
    }
}
