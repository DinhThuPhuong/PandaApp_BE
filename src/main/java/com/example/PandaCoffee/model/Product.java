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

//    @JoinColumn(name = "avatar_id", referencedColumnName = "id")
@OneToOne
    private Images avatar ;



    //    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
//    private List<Details> details;
    @ManyToOne
    private Categories categories;
}
