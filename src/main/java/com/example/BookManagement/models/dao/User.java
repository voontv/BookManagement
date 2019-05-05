package com.example.BookManagement.models.dao;

import lombok.Data;
import lombok.NonNull;

import javax.persistence.*;

@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(nullable = false)
    @NonNull
    private String email;

    @Column(nullable = false)
    @NonNull
    private String password;

    private String lastName;
    private String firstName;
    private String avatar;
}
