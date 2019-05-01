package com.example.BookManagement.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO {

    @NonNull
    int id;

    @Length(min=1, max = 255)
    @NonNull
    @NotBlank(message = "Name is mandatory")
    String name;
}
