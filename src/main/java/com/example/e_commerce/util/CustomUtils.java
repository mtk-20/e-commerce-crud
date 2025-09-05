package com.example.e_commerce.util;

import com.example.e_commerce.dto.*;
import com.example.e_commerce.entity.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CustomUtils {

    //user
    public UserDto toUserDto(User user) {
        return new UserDto(
                user.getUserId(),
                user.getUserName(),
                user.getPassword(),
                user.getEmail(),
                user.getRole()
        );
    }

    public User toUserEntity(UserDto userDto) {
        User user = new User();
        user.setUserId(userDto.getUserId());
        user.setUserName(userDto.getUserName());
        user.setPassword(userDto.getPassword());
        user.setEmail(userDto.getEmail());
        user.setRole(userDto.getRole());
        return user;
    }

    //category
    public CategoryDto tocategoryDto(Category category) {
        return new CategoryDto(
                category.getCategoryId(),
                category.getName()
        );
    }

    public Category toCategoryEntity(CategoryDto categoryDto) {
        Category category = new Category();
        category.setCategoryId(categoryDto.getCategoryId());
        category.setName(categoryDto.getName());
        return category;
    }

    public CategoryAndProductDto toCategoryAndProduct(Category category) {
        CategoryAndProductDto categoryAndProductDto = new CategoryAndProductDto();
        categoryAndProductDto.setCategoryId(category.getCategoryId());
        categoryAndProductDto.setName(category.getName());

        List<ProductDto> productDto = category.getProducts()
                .stream()
                .map(p -> new ProductDto(
                        p.getProductId(),
                        p.getName(),
                        p.getPrice(),
                        p.getStock(),
                        //
                        p.getCategory().getCategoryId()
                )).collect(Collectors.toList());
        categoryAndProductDto.setProductDto(productDto);
        return categoryAndProductDto;
    }

    //product
    public ProductDto toProductDto(Product product) {
        return new ProductDto(
                product.getProductId(),
                product.getName(),
                product.getPrice(),
                product.getStock(),
                product.getCategory().getCategoryId()
        );

    }

    public Product toProductEntity(ProductDto productDto) {
        Product product = new Product();
        product.setProductId(productDto.getProductId());
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setStock(productDto.getStock());
        Category category = new Category();
        category.setCategoryId(productDto.getCategoryId());
        product.setCategory(category);
        return product;
    }

    //Order
    public static OrderDto toOrderDto(Order order) {
        OrderDto orderDto = new OrderDto();
        orderDto.setOrderId(order.getOrderId());
        orderDto.setTotalPrice(order.getTotalPrice());
        orderDto.setOrderStatus(order.getOrderStatus());
        orderDto.setUserId(order.getUser().getUserId());
        orderDto.setOrderItemDto(order.getOrderItems().stream().map(CustomUtils::toOrderItemDto).collect(Collectors.toList()));
        return orderDto;
    }

    public static OrderItemDto toOrderItemDto(OrderItem orderItem) {
        OrderItemDto orderItemDto = new OrderItemDto();
        orderItemDto.setProductId(orderItem.getProduct().getProductId());
        orderItemDto.setPrice(orderItem.getPrice());
        orderItemDto.setQuantity(orderItem.getQuantity());
        return orderItemDto;
    }
}
