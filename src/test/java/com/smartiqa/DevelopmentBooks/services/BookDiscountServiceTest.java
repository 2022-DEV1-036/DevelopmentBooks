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
  public void checkNumberOfDifferentBookInTheBasket() throws Exception {
    List<Book> bookList = new ArrayList<Book>();
    Object discount = bookDiscountService.calculateDiscount(bookList);
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
    int numberOfDifferentBooks = bookDiscountService.calculateNumberOfDiffrentBooks(bookList);

    assertEquals(5, numberOfDifferentBooks);
  }

}
