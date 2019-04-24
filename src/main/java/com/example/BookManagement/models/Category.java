package com.example.BookManagement.models;

import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.persistence.Entity;

@Data
@Entity
public class Category {
    @Id
    int id;

}
