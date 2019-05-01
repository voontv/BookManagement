package com.example.BookManagement.controllers;

import com.example.BookManagement.converters.bases.Converter;
import com.example.BookManagement.exceptions.NotFoundException;
import com.example.BookManagement.models.dao.Author;
import com.example.BookManagement.models.dto.AuthorDTO;
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
    private Converter<Author, AuthorDTO> authorAuthorDTOConverter;

    @Autowired
    private Converter<AuthorDTO, Author> authorDTOAuthorConverter;

    @Autowired
    private AuthorRepository authorRepository;

    @GetMapping()
    List<AuthorDTO> get() {
        return  authorAuthorDTOConverter.convert(authorRepository.findAll(Sort.by("id")));
    }

    @GetMapping("/{id}")
    AuthorDTO get(@PathVariable int id) {

        if(authorRepository.findById(id).isPresent()) {
            return authorAuthorDTOConverter.convert(authorRepository.findById(id).get());
        }
        throw new NotFoundException(String.format("Author id %d not found",id));
    }

    @PostMapping
    void post(@Valid @RequestBody AuthorDTO authorDTO) {
        authorDTO.setId(0);
        authorRepository.save(authorDTOAuthorConverter.convert(authorDTO));
    }

    @PutMapping
    void  put(@RequestBody AuthorDTO authorDTO) {

        if(!authorRepository.findById(authorDTO.getId()).isPresent()) {
            throw new NotFoundException(String.format("Author have id %d not found",authorDTO.getId()));
        } else {
            authorRepository.save(authorDTOAuthorConverter.convert(authorDTO));
        }
    }

    @DeleteMapping("/{id}")
    void deleted(@PathVariable int id) {

       if(!authorRepository.findById(id).isPresent()) {
           throw new NotFoundException(String.format("Author have id %d not found",id));
       }
       authorRepository.deleteById(id);
   }
}
