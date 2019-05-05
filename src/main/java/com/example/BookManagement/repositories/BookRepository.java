package com.example.BookManagement.repositories;

import com.example.BookManagement.models.dao.Book;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BookRepository extends JpaRepository<Book, Integer> {
}
