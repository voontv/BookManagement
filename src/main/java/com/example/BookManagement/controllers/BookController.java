package com.example.BookManagement.controllers;

import com.example.BookManagement.converters.bases.Converter;
import com.example.BookManagement.exceptions.NotFoundException;
import com.example.BookManagement.models.dao.Book;
import com.example.BookManagement.models.dto.BookDTO;
import com.example.BookManagement.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private Converter<Book, BookDTO> bookBookDTOConverter;

    @Autowired
    private Converter<BookDTO, Book> bookDTOBookConverter;

    @GetMapping
    List<BookDTO> get() {

        return bookBookDTOConverter.convert(bookRepository.findAll(Sort.by("id")));
    }

    @GetMapping("/{id}")
     BookDTO get(@PathVariable int id) {

        Optional<Book> optionalBook = bookRepository.findById(id);

        if(optionalBook.isPresent()) {
            return bookBookDTOConverter.convert(optionalBook.get());
        } else {
            throw new NotFoundException(String.format("Book have id %d not found",id));
        }
    }

    @DeleteMapping("/{id}")
    void delete(@PathVariable int id) {

        if(bookRepository.findById(id).isPresent()) {
            bookRepository.deleteById(id);
        } else {
            throw new NotFoundException(String.format("Book have id %d not found",id));
        }
    }

    @PostMapping
    void post(@RequestBody BookDTO bookDTO) {

        bookDTO.setId(0);
        Date date = new Date();
        Book book = bookDTOBookConverter.convert(bookDTO);
        book.setCreatedAt(date);

        bookRepository.save(book);
    }

    @PutMapping
    void put(@RequestBody BookDTO bookDTO) {

        Date date = new Date();
        if(bookRepository.findById(bookDTO.getId()).isPresent()) {
            Book book = bookDTOBookConverter.convert(bookDTO);
            book.setUpdateAt(date);

            bookRepository.save(book);
        } else {
            throw new NotFoundException(String.format("Book have id %d not found",bookDTO.getId()));
        }
    }

}
