package com.example.BookManagement.controllers;

import com.example.BookManagement.converters.bases.Converter;
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

    @Autowired
    private Converter<Category, CategoryDTO> categoryCategoryDTOConverter;

    @Autowired
    private Converter<CategoryDTO, Category> categoryDTOCategoryConverter;

    @GetMapping()
    List<CategoryDTO> get() {
        return categoryCategoryDTOConverter.convert(categoryRepository.findAll(Sort.by("id")));
    }

    @GetMapping("/{id}")
    CategoryDTO get(@PathVariable int id) {

        if(categoryRepository.existsById(id))
        {
            return categoryCategoryDTOConverter.convert(categoryRepository.findById(id).get());
        } else {
            throw new NotFoundException(String.format("Category have id %d not found",id));
        }
    }

    @PostMapping()
    void post(@RequestBody CategoryDTO categoryDTO) {
        categoryRepository.save(categoryDTOCategoryConverter.convert(categoryDTO));
    }

    @PutMapping
    void put(@RequestBody CategoryDTO categoryDTO) {
        categoryRepository.save(categoryDTOCategoryConverter.convert(categoryDTO));
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
