package com.example.e_commerce.controller;

import com.example.e_commerce.dto.ProductDto;
import com.example.e_commerce.service.ProductService;
import com.example.e_commerce.util.CustomUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product")
public class ProductController {

    private final ProductService productService;
    private final CustomUtils customUtils;

    @PostMapping("/create")
    public ProductDto createProducts(@RequestBody ProductDto productDto) {
        return productService.createProduct(productDto);
    }

    @GetMapping
    public List<ProductDto> getAllProducts() {
        return productService.getAllProduct();
    }
}
