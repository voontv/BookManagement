package com.example.BookManagement.controllers;

import com.example.BookManagement.models.dao.Category;
import com.example.BookManagement.repositories.CategoryRepository;
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
public class CategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private Category category1;
    private Category category2;

    @Autowired
    private CategoryRepository categoryRepository;

    @Before
    public void initDataTest() {
        category1 = new Category(1,"Sport");
        category1 = categoryRepository.save(category1);

        category2 = new Category(2,"Loves");
        category2 = categoryRepository.save(category2);
    }

    @After
    public void deletedDataTest() {
        categoryRepository.deleteAll();
    }

    @Test
    public void testGetFound() throws Exception {

        mockMvc.perform(get("/api/categories"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", Matchers.hasSize(2)))
                .andExpect(jsonPath("$[0].id", Matchers.equalTo(category1.getId())))
                .andExpect(jsonPath("$[0].name", Matchers.equalTo(category1.getName())))
                .andExpect(jsonPath("$[1].id", Matchers.equalTo(category2.getId())))
                .andExpect(jsonPath("$[1].name", Matchers.equalTo(category2.getName())));
    }

    @Test
    public void testGetIdFound() throws  Exception {

        mockMvc.perform(get("/api/categories/"+category1.getId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", Matchers.equalTo(category1.getId())))
                .andExpect(jsonPath("$.name", Matchers.equalTo(category1.getName())));
    }

    @Test
    public void testGetIdNotFound() throws Exception {

        mockMvc.perform(get("/api/categories/"+category1.getId()*40))
                .andExpect((status().isNotFound()));
    }

    @Test
    public void testPost() throws Exception {
        Gson gson = new Gson();

        Category category = new Category(24,"Sex");
        categoryRepository.save(category);

        String json = gson.toJson(category);

        mockMvc.perform(post("/api/categories")
                .contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isOk());

        List<Category> categories = categoryRepository.findAll(Sort.by("id"));
        assertEquals(category.getName(), categories.get(3).getName());
    }

    @Test
    public void testPut() throws Exception {
        Gson gson = new Gson();

        category2.setName("Football");
        category2 = categoryRepository.save(category2);

        String json = gson.toJson(category2);

        mockMvc.perform(put("/api/categories")
                .contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isOk());

        assertEquals(category2.getName(), "Football");
    }

    @Test
    public void testPutNotFound() throws Exception {
        Gson gson = new Gson();

        Category category = new Category(34,"Nguyen Thien Nhan");

        String json = gson.toJson(category);

        mockMvc.perform(put("/api/categories")
                .contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isNotFound());
    }
    @Test
    public void testDeleteFound() throws Exception {

        mockMvc.perform(delete("/api/categories/"+category1.getId()))
                .andDo(print())
                .andExpect(status().isOk());

        assertFalse(categoryRepository.existsById(category1.getId()));
    }

    @Test
    public void testDeleteNotFound() throws Exception {

        mockMvc.perform(delete("/api/categories/"+category1.getId()*40))
                .andDo(print())
                .andExpect(status().isNotFound());
    }
}
