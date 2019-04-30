package com.example.BookManagement.controllers;

import com.example.BookManagement.exceptions.NotFoundException;
import com.example.BookManagement.models.dao.Category;
import com.example.BookManagement.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping()
    List<Category> get() {
        if(categoryRepository.findAll().isEmpty()) {
            throw new NotFoundException("Database is null now");
        }
        return categoryRepository.findAll();
    }

    @PostMapping("/id")
    void addNew(@RequestBody Category category) {
        categoryRepository.save(category);
    }
}
