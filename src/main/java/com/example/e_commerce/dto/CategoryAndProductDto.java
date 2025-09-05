package com.example.e_commerce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryAndProductDto {
    private int categoryId;
    private String name;
    private List<ProductDto> productDto;
}
