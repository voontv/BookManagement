package com.example.BookManagement.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Entity
@NoArgsConstructor
@RequiredArgsConstructor
public class Author {

    @Id
    int id;

    @Column(nullable = false)
    @NonNull
    @NotBlank(message = "Name is mandatory")
    String name;
}
