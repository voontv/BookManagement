package com.example.BookManagement.controllers;

import com.example.BookManagement.models.dao.Author;
import com.example.BookManagement.repositories.AuthorRepository;
import com.google.gson.Gson;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Assert;
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

import java.util.ArrayList;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@WebAppConfiguration
@EnableAutoConfiguration(exclude = {org.springframework.boot.autoconfigure.gson.GsonAutoConfiguration.class})
public class AuthorControllersTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AuthorRepository authorRepository;

    private Author author;
    private Author author1;

    @Before
    public void Init() {
        author = new Author(1,"Vo Quang Hoa");
        author = authorRepository.save(author);

        author1 = new Author(2,"Truong Van Voon");
        author1 = authorRepository.save(author1);
    }

    @After
    public void deleteAll() {
        authorRepository.deleteAll();
    }

    @Test
    public void testGetAll() throws Exception {
        mockMvc.perform(get("/api/authors"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", Matchers.hasSize(2)))
                .andExpect(jsonPath("$[0].id", Matchers.equalTo(author.getId())))
                .andExpect(jsonPath("$[0].name", Matchers.equalTo(author.getName())))
                .andExpect(jsonPath("$[1].id", Matchers.equalTo(author1.getId())))
                .andExpect(jsonPath("$[1].name", Matchers.equalTo(author1.getName())));
    }

    @Test
    public void testGetAllNull()  throws Exception {
        authorRepository.deleteAll();
        mockMvc.perform(get("/api/authors"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void testGetIdFound() throws Exception {
        mockMvc.perform(get("/api/authors/"+author1.getId()))
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", Matchers.equalTo(author1.getName())));
    }

    @Test
    public void testGetIdNotFound() throws Exception {
        mockMvc.perform(get("/api/authors/"+author.getId()+author1.getId()))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testPost() throws Exception {
        Gson gson = new Gson();

        Author author2 = new Author(6,"Vo Thi Quy");
        author2 = authorRepository.save(author2);

        String json = gson.toJson(author2);

        mockMvc.perform(post("/api/authors")
                .contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isOk());

        ArrayList<Author> authors = (ArrayList<Author>) authorRepository.findAll(Sort.by(Sort.Order.asc("id")));
        Author author3 = authors.get(authors.size()-1);

        Assert.assertEquals(author3.getName(), "Vo Thi Quy");
    }

    @Test
    public void testPutIdNameFound() throws Exception {
        Gson gson = new Gson();
        author.setName("Nguyen Thi Thu Thuy");
        String json = gson.toJson(author);

        mockMvc.perform(put("/api/authors")
                .contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isOk());
        Assert.assertEquals(author.getName(), "Nguyen Thi Thu Thuy");
    }

    @Test
    public void testDeleteNotFound() throws Exception {
        mockMvc.perform(delete("/api/authors/"+author.getId()+author1.getId()))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void testDeleteFound() throws Exception {
        mockMvc.perform(delete("/api/authors/"+author.getId()))
                .andExpect(status().isOk());

        Assert.assertFalse(authorRepository.findById(author.getId()).isPresent());
    }
}
