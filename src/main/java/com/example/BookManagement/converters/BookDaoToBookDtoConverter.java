package com.example.BookManagement.converters;

import com.example.BookManagement.converters.bases.Converter;
import com.example.BookManagement.exceptions.BadRequestException;
import com.example.BookManagement.models.dao.Book;
import com.example.BookManagement.models.dto.BookDTO;
import org.springframework.stereotype.Component;

@Component
public class BookDaoToBookDtoConverter extends Converter<Book, BookDTO> {

    @Override
    public BookDTO convert(Book book) throws BadRequestException {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setId(book.getId());
        bookDTO.setAuthor(book.getAuthor());
        bookDTO.setCategories(book.getCategories());
        bookDTO.setCover(book.getCover());
        bookDTO.setDescription(book.getDescription());
        bookDTO.setPrice(book.getPrice());
        bookDTO.setPublishYear(book.getPublishYear());
        bookDTO.setTitle(book.getTitle());

        return bookDTO;
    }
}
