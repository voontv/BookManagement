package com.example.BookManagement.converters;

import com.example.BookManagement.converters.bases.Converter;
import com.example.BookManagement.exceptions.BadRequestException;
import com.example.BookManagement.models.dao.Book;
import com.example.BookManagement.models.dto.BookDTO;
import org.springframework.stereotype.Component;

@Component
public class BookDtoToBookDaoConverter extends Converter<BookDTO, Book> {

    @Override
    public Book convert(BookDTO bookDTO) throws BadRequestException {
        Book book = new Book();
        book.setId(bookDTO.getId());
        book.setAuthor(bookDTO.getAuthor());
        book.setCategories(bookDTO.getCategories());
        book.setCover(bookDTO.getCover());
        book.setCreatedAt(bookDTO.getCreatedAt());
        book.setDescription(bookDTO.getDescription());
        book.setPrice(bookDTO.getPrice());
        book.setPublishYear(bookDTO.getPublishYear());
        book.setTitle(bookDTO.getTitle());
        book.setUpdateAt(bookDTO.getUpdateAt());

        return book;
    }
}
