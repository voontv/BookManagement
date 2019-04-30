package com.example.BookManagement.models.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class AuthorDTO {
    int id;

    @Length(min = 6, max = 12)
    String name;
}
