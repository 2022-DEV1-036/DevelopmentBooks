package com.smartiqa.DevelopmentBooks.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smartiqa.DevelopmentBooks.models.Book;
import com.smartiqa.DevelopmentBooks.services.BookDiscountServiceImpl;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.util.NestedServletException;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest
public class BascketDiscountControllerTest {

  //Inject Support Utils
  private static MockHttpServletRequest request;
  @Autowired
  MockMvc mockMvc;
  @Autowired
  ObjectMapper objectMapper;
  public static final MediaType APPLICATION_JSON_UTF8 = MediaType.APPLICATION_JSON;

  // Inject services
  @Autowired
  BookDiscountServiceImpl bookDiscountServiceImpl;


  @BeforeAll
  public static void setup() {
    request = new MockHttpServletRequest();
  }

  @Test
  public void shouldReturnTheExpectedResponse() throws Exception {
    List<Book> bookList = new ArrayList<Book>();
    Book book = new Book("Clean Code (Robert Martin, 2008)", 50.00);
    bookList.add(book);
    mockMvc.perform(post("/api/v1/discount")
      .contentType(MediaType.APPLICATION_JSON)
      .content(objectMapper.writeValueAsString(bookList)))
      .andExpect(status().isOk())
      .andExpect(content().contentType(APPLICATION_JSON_UTF8))
      .andExpect(jsonPath("$.basketAmount", isA(Double.class)));

  }

  @Test
  public void shouldReturnTheListOfBooks() throws Exception {
    List<Book> bookList = new ArrayList<Book>();
    Book book1 = new Book("Clean Code (Robert Martin, 2008)", 50.00);
    Book book2 = new Book("The Clean Coder (Robert Martin, 2011)", 50.00);
    Book book3 = new Book("Clean Architecture (Robert Martin, 2017)", 50.00);
    Book book4 = new Book("Test Driven Development by Example (Kent Beck, 2003)", 50.00);
    Book book5 = new Book("Working Effectively With Legacy Code (Michael C. Feathers, 2004)", 50.00);
    bookList.add(book1);
    bookList.add(book2);
    bookList.add(book3);
    bookList.add(book4);
    bookList.add(book5);

    Mockito.when(bookDiscountServiceImpl.getBooks()).thenReturn(bookList);

    mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/books")).andExpect(status().isOk()).andExpect(jsonPath("$", Matchers.hasSize(5)))
      .andExpect(jsonPath("$[0].bookName", Matchers.equalTo("Clean Code (Robert Martin, 2008)")))
      .andExpect(jsonPath("$[0].bookPrice", Matchers.equalTo(50.00)))
      .andExpect(jsonPath("$[1].bookName", Matchers.equalTo("The Clean Coder (Robert Martin, 2011)")))
      .andExpect(jsonPath("$[1].bookPrice", Matchers.equalTo(50.00)))
      .andExpect(jsonPath("$[2].bookName", Matchers.equalTo("Clean Architecture (Robert Martin, 2017)")))
      .andExpect(jsonPath("$[2].bookPrice", Matchers.equalTo(50.00)))
      .andExpect(jsonPath("$[3].bookName", Matchers.equalTo("Test Driven Development by Example (Kent Beck, 2003)")))
      .andExpect(jsonPath("$[4].bookPrice", Matchers.equalTo(50.00)))
      .andExpect(jsonPath("$[5].bookName", Matchers.equalTo("Working Effectively With Legacy Code (Michael C. Feathers, 2004)")))
      .andExpect(jsonPath("$[5].bookPrice", Matchers.equalTo(50.00)));
  }


}
