package com.example.BookManagement.models.dao;

import com.example.BookManagement.models.dao.Category;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.validation.Validator;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@WebAppConfiguration
@EnableAutoConfiguration(exclude = {org.springframework.boot.autoconfigure.gson.GsonAutoConfiguration.class})
public class CategoryTest {

    @Autowired
    private Validator validator;

    @Test
    public void test_CategoryOk() {
        Category category = new Category(1,"Love");
        Assert.assertTrue(validator.validate(category).isEmpty());
    }

    @Test
    public  void test_CategoryName() {
        Category category = new Category(2,"Sport");
        Assert.assertTrue(validator.validate(category).isEmpty());

        category.setName("       ");
        Assert.assertFalse(validator.validate(category).isEmpty());

        category.setName("");
        Assert.assertFalse(validator.validate(category).isEmpty());
    }
}
