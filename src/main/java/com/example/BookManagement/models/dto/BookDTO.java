package com.example.BookManagement.models.dto;

import com.example.BookManagement.models.dao.Author;
import com.example.BookManagement.models.dao.Category;
import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class BookDTO {

    @NonNull
    private int id;

    @NonNull
    @NotBlank(message = "Name is mandatory")
    private String title;

    Author author;

    List<Category> categories = new ArrayList<>();

    @NonNull
    @Min(value = 1980,message = "Invalid year")
    private int publishYear;

    @NonNull
    @Min(value = 0, message = "Invalid price")
    private int price;

    private String description;
    private  String cover;
}

