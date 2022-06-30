package com.smartiqa.DevelopmentBooks.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smartiqa.DevelopmentBooks.models.Book;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

@AutoConfigureMockMvc
@SpringBootTest
public class BascketServiceTest {
  //Inject Support Utils
  private static MockHttpServletRequest request;
  @Autowired
  MockMvc mockMvc;
  @Autowired
  ObjectMapper objectMapper;
  public static final MediaType APPLICATION_JSON_UTF8 = MediaType.APPLICATION_JSON;

  // Inject services
  @Autowired
  BookDiscountService bookDiscountService;

  @BeforeAll
  public static void setup() {
    request = new MockHttpServletRequest();
  }


  @Test
  public void checkPriceForOneBook() throws Exception {
    List<Book> bookList = new ArrayList<Book>();
    Book book = new Book("Clean Code (Robert Martin, 2008)", 50.00);
    bookList.add(book);
    Double  discount = bookDiscountService.calculateDiscount(bookList);

  }
}
