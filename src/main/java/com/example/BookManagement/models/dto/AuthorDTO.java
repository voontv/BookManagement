package com.example.BookManagement.models.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class AuthorDTO {

    @NonNull
    int id;

    @NonNull
    @NotBlank(message = "Name is mandatory")
    @Length(min = 6, max = 12)
    String name;
}
