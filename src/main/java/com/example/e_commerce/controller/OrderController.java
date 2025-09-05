package com.example.e_commerce.controller;

import com.example.e_commerce.dto.OrderDto;
import com.example.e_commerce.entity.Order;
import com.example.e_commerce.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/order")
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/create")
    public Order createOrders(@RequestBody OrderDto orderDto) {
        return orderService.createOrder(orderDto);
    }

    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.getAllOrder();
    }

    @GetMapping("/{userId}")
    public List<OrderDto> getOrdersByUserId(@PathVariable("userId") int userId) {
        return orderService.getOrderByUserId(userId);
    }
}
