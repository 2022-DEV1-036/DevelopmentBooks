package com.smartiqa.DevelopmentBooks.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smartiqa.DevelopmentBooks.models.Book;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.MethodArgumentNotValidException;

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

  /* Test : shouldThrowExceptionWhenABookPriceOrBookNameAreEmptyOrNull
   * */
  @Test
  public void shouldThrowExceptionWhenBookPriceOrBookNameAreEmptyOrNull() throws Exception {
    Assertions.assertThrows(MethodArgumentNotValidException.class, () -> {
      List<Book> bookList = new ArrayList<Book>();
      Book book1 = new Book(null,null);
      Book book2 = new Book();
      Book book3 = new Book(null,null);
      Book book4 = new Book();
      bookList.add(book1);
      bookList.add(book2);
      bookList.add(book3);
      bookList.add(book4);

      mockMvc.perform(post("/api/v1/discount")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(bookList)));});
  }



}
