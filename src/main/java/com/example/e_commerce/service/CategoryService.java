package com.example.e_commerce.service;

import com.example.e_commerce.dto.CategoryAndProductDto;
import com.example.e_commerce.dto.CategoryDto;
import com.example.e_commerce.exception.UserIdAlreadyExistException;
import com.example.e_commerce.exception.UserIdNotFoundException;
import com.example.e_commerce.repository.CategoryRepo;
import com.example.e_commerce.util.CustomUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepo categoryRepo;
    private final CustomUtils customUtils;

    public CategoryDto createCategory(CategoryDto categoryDto) {
        if (categoryRepo.existsById(categoryDto.getCategoryId())) {
            throw new UserIdAlreadyExistException("Category id already exist.");
        }
        return customUtils.tocategoryDto(categoryRepo.save(customUtils.toCategoryEntity(categoryDto)));
    }

    public List<CategoryDto> getAllCategory() {
        return categoryRepo.findAll().stream().map(customUtils::tocategoryDto).collect(Collectors.toList());
    }

    public CategoryDto getCategoryById(int id) {
        return customUtils.tocategoryDto(categoryRepo.findById(id).orElseThrow(() -> new UserIdNotFoundException("No category-id " + id)));
    }

    public CategoryAndProductDto getCategoryWithProduct(int id) {
        return customUtils.toCategoryAndProduct(categoryRepo.findById(id).orElseThrow(() -> new UserIdNotFoundException("Category id not found.")));
    }
}
