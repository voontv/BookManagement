package com.example.BookManagement.controllers;

import com.example.BookManagement.exceptions.NotFoundException;
import com.example.BookManagement.models.dao.Author;
import com.example.BookManagement.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {

    @Autowired
    private AuthorRepository authorRepository;

    @GetMapping()
    List<Author> get() {
        return  authorRepository.findAll(Sort.by("id"));
    }

    @GetMapping("/{id}")
    Author get(@PathVariable int id) {

        if(authorRepository.existsById(id)) {
            return authorRepository.findById(id).get();
        } else {
            throw new NotFoundException(String.format("Author id %d not found",id));
        }
    }

    @PostMapping
    void post(@Valid @RequestBody Author author) {
        authorRepository.save(author);
    }

    @PutMapping()
    void  put(@RequestBody Author author) {
        authorRepository.save(author);
    }

    @DeleteMapping("/{id}")
    void deleted(@PathVariable int id) {

       if(!authorRepository.existsById(id)) {
           throw new NotFoundException(String.format("Author have id %d not found",id));
       } else {
           authorRepository.deleteById(id);
       }
   }
}
