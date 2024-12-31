package com.example.PandaCoffee.model;

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
    @JoinColumn(name = "bill_id", referencedColumnName = "billId")
    private Bill bill;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "productId")
    private Product product;

    private int quantity;
}
