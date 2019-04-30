package com.example.BookManagement.converters;

import com.example.BookManagement.converters.bases.Converter;
import com.example.BookManagement.exceptions.BadRequestException;
import com.example.BookManagement.models.dao.Author;
import com.example.BookManagement.models.dto.AuthorDTO;
import org.springframework.stereotype.Component;

@Component
public class AuthorDaotoAuthorDtoConverter extends Converter<Author, AuthorDTO> {

    @Override
    public AuthorDTO convert(Author author) throws BadRequestException {
        AuthorDTO authorDTO = new AuthorDTO();
        authorDTO.setId(author.getId());
        authorDTO.setName(author.getName());
        return authorDTO;
    }

}
