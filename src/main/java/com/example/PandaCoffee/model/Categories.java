package com.example.PandaCoffee.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "categories")
public class Categories {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int categoryId;

    private String categoryName;
    @OneToOne
    private Image image;

    @OneToMany(mappedBy = "categories", cascade = CascadeType.ALL, orphanRemoval = true)
//    @JsonManagedReference // Chỉ định quan hệ một chiều từ User sang CV
    private List<Product> listCV; // Danh sách CV chính của User

}
