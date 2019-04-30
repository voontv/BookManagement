package com.example.BookManagement.controllers;

import com.example.BookManagement.exceptions.NotFoundException;
import com.example.BookManagement.models.dao.Category;
import com.example.BookManagement.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping()
    List<Category> get() {
        return categoryRepository.findAll(Sort.by("id"));
    }

    @GetMapping("/{id}")
    Category get(@PathVariable int id) {

        if(categoryRepository.existsById(id))
        {
            return categoryRepository.findById(id).get();
        } else {
            throw new NotFoundException(String.format("Category have id %d not found",id));
        }
    }

    @PostMapping()
    void post(@RequestBody Category category) {
        categoryRepository.save(category);
    }

    @PutMapping
    void put(@RequestBody Category category) {
        categoryRepository.save(category);
    }

    @DeleteMapping("/{id}")
    void delete(@PathVariable int id) {

        if(categoryRepository.existsById(id)) {
            categoryRepository.deleteById(id);
        } else {
            throw new NotFoundException(String.format("Category have id %d not found",id));
        }
    }
}
