package com.example.e_commerce.controller;

import com.example.e_commerce.dto.CategoryAndProductDto;
import com.example.e_commerce.dto.CategoryDto;
import com.example.e_commerce.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/category")
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping("/create")
    public CategoryDto createCategories(@RequestBody CategoryDto categoryDto) {
        return categoryService.createCategory(categoryDto);
    }

    @GetMapping
    public List<CategoryDto> getAllCategories() {
        return categoryService.getAllCategory();
    }

    @GetMapping("/{id}")
    public CategoryDto getCategoriesById(@PathVariable("id") int id) {
        return categoryService.getCategoryById(id);
    }

    @GetMapping("/product/{id}")
    public CategoryAndProductDto getCategoryWithProducts(@PathVariable("id") int id) {
        return categoryService.getCategoryWithProduct(id);
    }

}
