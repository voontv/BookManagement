package com.example.BookManagement.models.dao;

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
@EnableAutoConfiguration
public class BookTest {

    @Autowired
    private Validator validator;
    private Book book;

    @Test
    public void testOk() {
        book = new Book(1,"Must be try learn",2008,98,"voontv");
        Assert.assertTrue(validator.validate(book).isEmpty());
    }

    @Test
    public void testName() {
        book = new Book(1,"Must be try learn",2008,98,"voontv");
        Assert.assertTrue(validator.validate(book).isEmpty());

        book.setTitle("");
        Assert.assertFalse(validator.validate(book).isEmpty());

        book.setTitle("            ");
        Assert.assertFalse(validator.validate(book).isEmpty());
    }

    @Test
    public void testPublicYear() {
        book = new Book(1,"Must be try learn",2008,98,"voontv");
        Assert.assertTrue(validator.validate(book).isEmpty());

        book.setPublishYear(1978);
        Assert.assertFalse(validator.validate(book).isEmpty());

        book.setPublishYear(2020);
        Assert.assertFalse(validator.validate(book).isEmpty());
    }

    @Test
    public void testPrice() {
        book = new Book(1,"Must be try learn",2008,98,"voontv");
        Assert.assertTrue(validator.validate(book).isEmpty());

        book.setPrice(-9);
        Assert.assertFalse(validator.validate(book).isEmpty());
    }
}
