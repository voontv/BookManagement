package com.example.BookManagement.repositories;

import com.example.BookManagement.models.dao.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Integer> {
}
