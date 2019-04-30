package com.example.BookManagement.repositories;

import com.example.BookManagement.models.dao.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Author, Integer> {

    List<Author> getAllByNameContains(String name);

    @Transactional
    @Modifying
    @Query("update Author set name = :newName where id = :id")
    void updateName(int id, String newName);
}
