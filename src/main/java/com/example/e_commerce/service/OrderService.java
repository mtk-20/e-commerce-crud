package com.example.e_commerce.service;

import com.example.e_commerce.dto.OrderDto;
import com.example.e_commerce.entity.Order;
import com.example.e_commerce.entity.OrderItem;
import com.example.e_commerce.entity.User;
import com.example.e_commerce.exception.NotEnoughStockException;
import com.example.e_commerce.exception.UserIdNotFoundException;
import com.example.e_commerce.repository.OrderRepo;
import com.example.e_commerce.repository.ProductRepo;
import com.example.e_commerce.repository.UserRepo;
import com.example.e_commerce.util.CustomUtils;
import lombok.RequiredArgsConstructor;
import lombok.var;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.naming.InsufficientResourcesException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final UserRepo userRepo;
    private final OrderRepo orderRepo;
    private final ProductRepo productRepo;

    @Transactional
    public Order createOrder(OrderDto orderDto) {
        var user = userRepo.findById(orderDto.getUserId()).orElseThrow(() -> new UserIdNotFoundException("No user id-" + orderDto.getUserId()));
        var order = new Order();

        order.setUser(user);
        order.setOrderStatus(Order.OrderStatus.PENDING);

        List<OrderItem> orderItems = orderDto.getOrderItemDto().stream().map(item -> {
            var product = productRepo.findById(item.getProductId()).orElseThrow(() -> new UserIdNotFoundException("Product id not found: " + item.getProductId()));
            product.setProductId(item.getProductId());
            if (product.getStock() < item.getQuantity()) {
                throw new NotEnoughStockException("Not enough stock.");
            }
            product.setStock(product.getStock() - item.getQuantity());
            productRepo.save(product);

            var orderItem = new OrderItem();
            orderItem.setQuantity(item.getQuantity());
            orderItem.setPrice(product.getPrice());
            orderItem.setOrder(order);
            orderItem.setProduct(product);
            return orderItem;
        }).collect(Collectors.toList());

        double totalPrice = orderItems.stream().mapToDouble(item -> item.getPrice() * item.getQuantity()).sum();

        order.setTotalPrice(totalPrice);
        order.setOrderItems(orderItems);

        return orderRepo.save(order);
    }

    public List<Order> getAllOrder() {
        return orderRepo.findAll();
    }

    public List<OrderDto> getOrderByUserId(int userId) {
        List<Order> orders = orderRepo.findByUserUserId(userId);
        return orders.stream().map(CustomUtils::toOrderDto).collect(Collectors.toList());
    }
}
