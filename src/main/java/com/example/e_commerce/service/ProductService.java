package com.example.e_commerce.service;

import com.example.e_commerce.dto.ProductDto;
import com.example.e_commerce.exception.UserIdAlreadyExistException;
import com.example.e_commerce.repository.ProductRepo;
import com.example.e_commerce.util.CustomUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepo productRepo;
    private final CustomUtils customUtils;

    public ProductDto createProduct(ProductDto productDto) {
        if (productRepo.existsById(productDto.getProductId())) {
            throw new UserIdAlreadyExistException("Product id already exists.");
        }
        return customUtils.toProductDto(productRepo.save(customUtils.toProductEntity(productDto)));
    }

    public List<ProductDto> getAllProduct() {
        return productRepo.findAll().stream().map(customUtils::toProductDto).collect(Collectors.toList());
    }
}
