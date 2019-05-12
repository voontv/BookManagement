package com.example.BookManagement.controllers;

import com.example.BookManagement.models.dao.Book;
import com.example.BookManagement.models.dao.Category;
import com.example.BookManagement.models.dto.BookDTO;
import com.example.BookManagement.repositories.BookRepository;
import com.google.gson.Gson;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@WebAppConfiguration
@EnableAutoConfiguration
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BookRepository bookRepository;

    private Book book1;
    private Book book2;

    @Before
    public void InitData() {

        book1 = new Book(1,"no pain no gain",1989,86, new Date());
        book1 = bookRepository.save(book1);

        book2 = new Book(2,"try try and try",1990,200, new Date());
        book2 = bookRepository.save(book2);
    }

    @After
    public void deleteAll() {
        bookRepository.deleteAll();
    }

    @Test
    public void testGet() throws Exception {

        mockMvc.perform(get("/api/books"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", Matchers.hasSize(2)))
                .andExpect(jsonPath("$[0].id", Matchers.equalTo(book1.getId())))
                .andExpect(jsonPath("$[0].title", Matchers.equalTo(book1.getTitle())))
                .andExpect(jsonPath("$[0].publishYear", Matchers.equalTo(book1.getPublishYear())))
                .andExpect(jsonPath("$[0].price", Matchers.equalTo(book1.getPrice())))
                .andExpect(jsonPath("$[1].id", Matchers.equalTo(book2.getId())))
                .andExpect(jsonPath("$[1].title", Matchers.equalTo(book2.getTitle())))
                .andExpect(jsonPath("$[1].publishYear", Matchers.equalTo(book2.getPublishYear())))
                .andExpect(jsonPath("$[1].price", Matchers.equalTo(book2.getPrice())));
    }

    @Test
    public void testGetIdFound() throws Exception {

        mockMvc.perform(get("/api/books/"+book2.getId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", Matchers.equalTo(book2.getId())))
                .andExpect(jsonPath("$.title", Matchers.equalTo(book2.getTitle())))
                .andExpect(jsonPath("$.publishYear", Matchers.equalTo(book2.getPublishYear())))
                .andExpect(jsonPath("$.price", Matchers.equalTo(book2.getPrice())));
    }

    @Test
    public void testGetIdNotFound() throws Exception {

        mockMvc.perform(get("/api/books/9999"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void testDeleteFound() throws Exception {
        Category category = new Category(1,"Loves");
        book1.getCategories().add(category);

        mockMvc.perform(delete("/api/books/"+book1.getId()))
                .andExpect(status().isOk());

        assertFalse(bookRepository.findById(book1.getId()).isPresent());
    }

    @Test
    public void testDeleteNotFound() throws Exception {

        mockMvc.perform(delete("/api/books/"+9999))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testPost() throws Exception {
        Gson gson = new Gson();

        BookDTO bookDTO = new BookDTO(6,"try try and try",2018,200);
        String json = gson.toJson(bookDTO);

        mockMvc.perform(post("/api/books")
                .contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isOk());

        List<Book> books = bookRepository.findAll(Sort.by("publishYear"));
        assertEquals(books.size(), 3);
        assertEquals(books.get(2).getPublishYear(), bookDTO.getPublishYear());
        assertEquals(books.get(2).getTitle(), bookDTO.getTitle());
    }

    @Test
    public void testPutFound() throws Exception {
        Gson gson = new Gson();

        BookDTO bookDTO = new BookDTO(book1.getId(),"Try learn Java, good for skill",2019,138);
        String json = gson.toJson(bookDTO);

        mockMvc.perform(put("/api/books")
                .contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isOk());

        List<Book> books = bookRepository.findAll(Sort.by("publishYear"));
        assertEquals(books.get(1).getTitle(), "Try learn Java, good for skill");
    }

    @Test
    public void testPutNotFound() throws Exception {
        Gson gson = new Gson();
        BookDTO bookDTO = new BookDTO(10000,"Try learn Java, good for skill",2019,138);

        String json = gson.toJson(bookDTO);
        mockMvc.perform(put("/api/books")
                .contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isNotFound());
    }
}
