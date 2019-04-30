package com.example.BookManagement.controllers;

import com.example.BookManagement.exceptions.NotFoundException;
import com.example.BookManagement.models.dao.Author;
import com.example.BookManagement.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {
    @Autowired
    private AuthorRepository authorRepository;
    @GetMapping("/all")
    List<Author> getAll() {

        if(!authorRepository.findAll(Sort.by(Sort.Order.asc("id"))).isEmpty()) {
            return  authorRepository.findAll(Sort.by(Sort.Order.asc("id")));
        }
        throw new NotFoundException("Database is null");
    }

    @GetMapping("/name")
    List<Author> getName(@RequestParam String name) {

        if(!authorRepository.getAllByNameContains(name).isEmpty()) {
            return authorRepository.getAllByNameContains(name);
        }
        throw new NotFoundException(String.format("Name %s can not found in database",name));
    }

    @GetMapping("/{id}")
    Author getId(@PathVariable int id) {

        if(authorRepository.findById(id).isPresent()) {
            return authorRepository.findById(id).get();
        }
        throw new NotFoundException(String.format("Author id %d not found",id));
    }

    @PostMapping
    void post(@Valid @RequestBody Author author) {
            authorRepository.save(author);
    }

    @PutMapping("/name")
    void  put(@RequestBody Author author) {
        authorRepository.save(author);
    }

    @DeleteMapping("/{id}")
    void deleted(@PathVariable int id) {

       if(!authorRepository.existsById(id)) {
           throw new NotFoundException(String.format("Author have id %d not found",id));
       }
       authorRepository.deleteById(id);
   }
}
