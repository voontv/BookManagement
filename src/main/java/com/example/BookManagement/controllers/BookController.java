package com.example.BookManagement.controllers;

import com.example.BookManagement.converters.bases.Converter;
import com.example.BookManagement.exceptions.NotFoundException;
import com.example.BookManagement.models.dao.Book;
import com.example.BookManagement.models.dto.BookDTO;
import com.example.BookManagement.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

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
        System.out.println("############## listsize"+bookBookDTOConverter.convert(bookRepository.findAll(Sort.by("id"))).size());
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
        bookRepository.save(bookDTOBookConverter.convert(bookDTO));
    }

    @PutMapping
    void put(@RequestBody BookDTO bookDTO) {

        if(bookRepository.findById(bookDTO.getId()).isPresent()) {
            bookRepository.save(bookDTOBookConverter.convert(bookDTO));
        } else {
            throw new NotFoundException(String.format("Book have id %d not found",bookDTO.getId()));
        }
    }

}
