package com.example.e_commerce.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {

    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int quantity;
    private double price;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "product_id")
    private Product product;

}
