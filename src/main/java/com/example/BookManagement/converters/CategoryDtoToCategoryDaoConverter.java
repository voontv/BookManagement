package com.example.BookManagement.converters;

import com.example.BookManagement.converters.bases.Converter;
import com.example.BookManagement.exceptions.BadRequestException;
import com.example.BookManagement.models.dao.Category;
import com.example.BookManagement.models.dto.CategoryDTO;
import org.springframework.stereotype.Component;

@Component
public class CategoryDtoToCategoryDaoConverter extends Converter<CategoryDTO, Category> {

    @Override
    public Category convert(CategoryDTO categoryDTO) throws BadRequestException {
        Category category = new Category();
        category.setId(categoryDTO.getId());
        category.setName(categoryDTO.getName());

        return category;
    }
}
