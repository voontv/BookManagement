package com.example.BookManagement.controllers;

import com.example.BookManagement.converters.bases.Converter;
import com.example.BookManagement.models.dao.Book;
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

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@WebAppConfiguration
@EnableAutoConfiguration(exclude = {org.springframework.boot.autoconfigure.gson.GsonAutoConfiguration.class})
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Converter<BookDTO, Book> bookDTOBookConverter;

    @Autowired
    private BookRepository bookRepository;
    private Book book1;
    private Book book2;
    private BookDTO bookDTO1;
    private BookDTO bookDTO2;

    private LocalDate localDate =  LocalDate.of(2017, 1, 13);


    private LocalDate localDate1 = LocalDate.of(1989, 12, 16);  ;

    @Before
    public void InitData() {

        book1 = new Book(1,"no pain no gain",1989,86, localDate);
        book1 = bookRepository.save(book1);

        book2 = new Book(2,"try try and try",1990,200, localDate);
        book2 = bookRepository.save(book2);

        bookDTO1 = new BookDTO(6,"try try and try",2018,200, localDate1);
        bookDTO2 = new BookDTO(7,"try try and try",2019,138, localDate1);
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

        mockMvc.perform(get("/api/books/"+bookDTO1.getId()))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void testDeleteFound() throws Exception {

        mockMvc.perform(delete("/api/books/"+book1.getId()))
                .andExpect(status().isOk());

        assertFalse(bookRepository.findById(book1.getId()).isPresent());
    }

    @Test
    public void testDeleteNotFound() throws Exception {

        mockMvc.perform(delete("/api/books/"+bookDTO1.getId()*1000))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testPost() throws Exception {
        Gson gson = new Gson();

        bookDTO1 = new BookDTO(6,"try try and try",2018,200, localDate1);
        String json = gson.toJson(bookDTO1);

        mockMvc.perform(post("/api/books")
                .contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isOk());

        List<Book> books = bookRepository.findAll(Sort.by("publishYear"));
        assertEquals(books.size(), 3);
        assertEquals(books.get(2).getPublishYear(), bookDTO1.getPublishYear());
        assertEquals(books.get(2).getTitle(), bookDTO1.getTitle());
    }

    @Test
    public void testPutFound() throws Exception {
        Gson gson = new Gson();

        String json = gson.toJson(bookDTO2);

        System.out.println("json "+json);

        mockMvc.perform(put("/api/books")
                .contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isOk());

        List<Book> books = bookRepository.findAll(Sort.by("publishYear"));
        assertEquals(books.get(1).getTitle(), "Try learn Java, good for skill");
    }

    @Test
    public void testPutNotFound() throws Exception {
        Gson gson = new Gson();
        String json = gson.toJson(bookDTO2);
        System.out.println("json "+json);
        System.out.println("json  "+json);
        mockMvc.perform(post("/api/books")
                .contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isNotFound());
    }
}
