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
@EnableAutoConfiguration(exclude = {org.springframework.boot.autoconfigure.gson.GsonAutoConfiguration.class})
public class AuthorTest {

    @Autowired
    private Validator validator;

    @Test
    public void test_AuthorOk() {
        Author author = new Author(1,"Truong Van Voon");
        Assert.assertTrue(validator.validate(author).isEmpty());

        Author author1 = new Author();
        Assert.assertFalse(validator.validate(author1).isEmpty());
    }

    @Test
    public void test_AuthorName() {
        Author author = new Author();
        author.setName("");
        Assert.assertFalse(validator.validate(author).isEmpty());

        author.setName("      ");
        Assert.assertFalse(validator.validate(author).isEmpty());

        author.setName("Ok");
        Assert.assertTrue(validator.validate(author).isEmpty());
    }
}
