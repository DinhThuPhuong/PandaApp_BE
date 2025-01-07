package com.example.PandaCoffee.model;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    private String name;
    private String email;
    private String password;
    private String phoneNumber;
    private boolean status;


    @OneToOne(cascade = CascadeType.REMOVE)
    private Images avatar;

    private String verificationCode; // Mã xác thực



    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonManagedReference // Quản lý vòng lặp với Bill
    private List<Bill> bills;
}
