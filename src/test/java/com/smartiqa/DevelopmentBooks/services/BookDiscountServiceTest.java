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

import static org.junit.jupiter.api.Assertions.assertEquals;

@AutoConfigureMockMvc
@SpringBootTest
public class BookDiscountServiceTest {
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
    Object discount = bookDiscountService.calculateDiscount(bookList);
    assertEquals(50.00, discount);

  }
  @Test
  public void checkPriceForEmptyBasket() throws Exception {
    List<Book> bookList = new ArrayList<Book>();
    Object discount = bookDiscountService.calculateDiscount(bookList);
    assertEquals("Your basket is empty !", discount);

  }

  @Test
  public void checkPriceForTwoSameBooksInBasket() throws Exception {
    List<Book> bookList = new ArrayList<Book>();
    Book book1 = new Book("Clean Code (Robert Martin, 2008)", 50.00);
    Book book2 = new Book("Clean Code (Robert Martin, 2008)", 50.00);
    bookList.add(book1);
    bookList.add(book2);
    Object discount = bookDiscountService.calculateDiscount(bookList);
    assertEquals(100.00, discount);

  }
}
