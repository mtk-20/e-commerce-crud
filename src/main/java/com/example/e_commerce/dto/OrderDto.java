package com.example.e_commerce.dto;

import com.example.e_commerce.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {

    private int orderId;
    private double totalPrice;
    private Order.OrderStatus orderStatus;
    private int userId;
    private List<OrderItemDto> orderItemDto;
}
