package com.example.BookManagement.repositories;

import com.example.BookManagement.models.dao.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
