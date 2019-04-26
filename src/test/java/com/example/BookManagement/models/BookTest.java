package com.example.BookManagement.models;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.Validator;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class BookTest {

    @Autowired
    private Validator validator;
    private Book book;

    @Test
    public void testOk() {
        book = new Book(1,"Must be try learn",2008,98,"voontv");
        Assert.assertTrue(validator.validate(book).isEmpty());
    }
}
