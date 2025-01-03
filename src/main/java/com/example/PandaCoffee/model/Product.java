package com.example.PandaCoffee.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor

@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productId;

    private String productName;
    private String description;
    private double sizeS;
    private double sizeM;
    private double sizeL;

    @OneToOne
    public Images avatar ;
    @ManyToOne
    private Categories categories;

    private int status;  // 1: Còn hàng, 0: Hết hàng, -1: Bị xóa
}
