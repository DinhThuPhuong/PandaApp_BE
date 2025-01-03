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

    @OneToOne
//    @JoinColumn(name = "avatar_id", referencedColumnName = "id")
    public Images avatar;


    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Bill> bills;
}
