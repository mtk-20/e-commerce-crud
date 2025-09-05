package com.example.e_commerce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

    private int productId;
    private String name;
    private double price;
    private int stock;
    //
    private int categoryId;
}
