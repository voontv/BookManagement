package com.example.BookManagement.models.dao;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@RequiredArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NonNull
    int id;

    @Column(nullable = false)
    @NonNull
    @NotBlank(message = "Name is mandatory")
    String title;

    @ManyToOne (cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    Author author;

    @ManyToMany
    Set<Category> categorys = new HashSet<Category>();

    @Column(nullable = false)
    @NonNull
    @Min(value = 1980,message = "Invalid year")
    @Max(value = 2019,message = "Invalid  year")
    int publishYear;

    @Column(nullable = false)
    @NonNull
    @Min(value = 0,message = "Invalid price")
    int price;

    String description;
    String cover;

    @Column(nullable =  false)
    @NonNull
    @NotBlank(message = "Value is mandatory")
    String createdAt;

    String updateAt;
}
