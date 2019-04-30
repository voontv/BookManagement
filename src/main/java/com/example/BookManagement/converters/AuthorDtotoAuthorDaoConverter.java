package com.example.BookManagement.converters;

import com.example.BookManagement.converters.bases.Converter;
import com.example.BookManagement.exceptions.BadRequestException;
import com.example.BookManagement.models.dao.Author;
import com.example.BookManagement.models.dto.AuthorDTO;
import org.springframework.stereotype.Component;

@Component
public class AuthorDtotoAuthorDaoConverter extends Converter<AuthorDTO, Author> {

    @Override
    public Author convert(AuthorDTO source) throws BadRequestException {
        Author author = new Author();
        author.setName(source.getName());
        author.setId(source.getId());

        return author;
    }
}
