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
@Table(name = "categories")
public class Categories {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int categoryId;

    private String categoryName;

    @OneToOne
//    @JoinColumn(name = "avatar_id", referencedColumnName = "id")
    public Images avatar ;



    @OneToMany(mappedBy = "categories", cascade = CascadeType.ALL, orphanRemoval = true)
//    @JsonManagedReference // Chỉ định quan hệ một chiều từ User sang CV
    private List<Product> listCV; // Danh sách CV chính của User

}
