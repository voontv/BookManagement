package com.example.BookManagement.models;

import lombok.Data;
import lombok.NonNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "user")
public class User {

    @Id
    int id;

    @Column(nullable = false)
    @NonNull
    String email;

    @Column(nullable = false)
    @NonNull
    String password;

    String lastName;
    String firstName;
    String avatar;
}
