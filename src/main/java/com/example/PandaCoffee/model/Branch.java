package com.example.PandaCoffee.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    @OneToOne
    private Images avatar;

    @OneToMany(mappedBy = "branch", cascade = CascadeType.ALL)
    @JsonManagedReference // Quản lý vòng lặp với Bill
    private List<Bill> bills;
}

