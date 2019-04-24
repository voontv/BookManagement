package com.example.BookManagement.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;


@Data
@Entity
@NoArgsConstructor
@RequiredArgsConstructor
public class Author {

    @Id
    int id;

    @Column(nullable = false)
    @NonNull
    String name;
}
