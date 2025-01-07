package com.example.PandaCoffee.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "details")
public class Details {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int detailId;



    @ManyToOne
    @JoinColumn(name = "bill_id")
    @JsonBackReference // Chỉ định đây là phía ngược lại của quan hệ, không tuần tự hóa User
    private Bill bill;




    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "productId")
    private Product product;

    private String size;

    private int quantity;
}
