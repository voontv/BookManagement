package com.example.BookManagement.models.dao;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@RequiredArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NonNull
    private int id;

    @Column(nullable = false)
    @NonNull
    @NotBlank(message = "Name is mandatory")
    private String title;

    @ManyToOne (cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    Author author;

    @ManyToMany
    List<Category> categories = new ArrayList<>();

    @Column(nullable = false)
    @NonNull
    @Min(value = 1980,message = "Invalid year")
    private int publishYear;

    @Column(nullable = false)
    @NonNull
    @Min(value = 0, message = "Invalid price")
    private int price;

    private String description;
    private  String cover;

    @Column(nullable = false)
    @NonNull
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate createdAt;

    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate updateAt;
}
