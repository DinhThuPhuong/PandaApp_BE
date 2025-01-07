package com.example.PandaCoffee.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

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
    @JsonBackReference // Tránh vòng lặp với User
    private User user;

    @ManyToOne
    @JoinColumn(name = "branch_id", referencedColumnName = "branchId")
    @JsonBackReference // Tránh vòng lặp với Branch
    private Branch branch;

    @OneToMany(mappedBy = "bill", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference // Chỉ định quan hệ một chiều từ User sang CV
    private List<Details> details;



}
