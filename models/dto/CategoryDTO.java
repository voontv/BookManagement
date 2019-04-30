package com.example.BookManagement.models.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class CategoryDTO {
    int id;

    @Length(min = 4, max = 12)
    String name;
}
