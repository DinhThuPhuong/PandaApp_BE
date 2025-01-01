package com.example.PandaCoffee.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
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

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Details> details;
}
