package com.example.BookManagement.converters;

import com.example.BookManagement.converters.bases.Converter;
import com.example.BookManagement.exceptions.BadRequestException;
import com.example.BookManagement.models.dao.Category;
import com.example.BookManagement.models.dto.CategoryDTO;
import org.springframework.stereotype.Component;

@Component
public class CategoryDaoToCategoryDtoConverter extends Converter<Category, CategoryDTO> {

    @Override
    public CategoryDTO convert(Category Category) throws BadRequestException {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(Category.getId());
        categoryDTO.setName(Category.getName());

        return categoryDTO;
    }
}
