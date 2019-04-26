package com.example.BookManagement.models.Dao;

import lombok.Data;
import lombok.NonNull;
import org.hibernate.annotations.ValueGenerationType;

import javax.persistence.*;

@Data
@Entity
@Table(name = "user")
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
