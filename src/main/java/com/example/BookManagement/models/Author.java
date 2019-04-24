package com.example.BookManagement.models;

import lombok.Data;
import lombok.NonNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Data
@Entity
public class Author {

    @Id
    int id;

    @Column(nullable = false)
    @NonNull
    String name;
}
