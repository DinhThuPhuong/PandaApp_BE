package com.example.PandaCoffee.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "bills")
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int billId;

    private double totalPrice;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "userId")
    private User user;

    @ManyToOne
    @JoinColumn(name = "branch_id", referencedColumnName = "branchId")
    private Branch branch;
}
