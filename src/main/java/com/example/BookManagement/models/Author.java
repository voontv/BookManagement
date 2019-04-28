package com.example.BookManagement.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;


@Data
@Entity
@NoArgsConstructor
@RequiredArgsConstructor
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NonNull
    private int id;

    @Column(nullable = false)
    @NonNull
    @NotBlank(message = "Name is mandatory")
    private String name;
}
