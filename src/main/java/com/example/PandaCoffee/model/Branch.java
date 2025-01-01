package com.example.PandaCoffee.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "branches")
public class Branch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int branchId;

    private String branchName;
    private String address;
    private String phoneNumber;
    private String imageUrl;

    @OneToMany(mappedBy = "branch", cascade = CascadeType.ALL)
    private List<Bill> bills;
}

