package com.smartiqa.DevelopmentBooks.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smartiqa.DevelopmentBooks.exceptions.EmptyBasketException;
import com.smartiqa.DevelopmentBooks.models.BasketDiscountPrice;
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

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@AutoConfigureMockMvc
@SpringBootTest
public class BookDiscountServiceImplTest {
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
  public void shouldReturnThePriceOfOneBookForOneBookBasket() throws Exception {
    List<Book> bookList = new ArrayList<Book>();
    Book book = new Book("Clean Code (Robert Martin, 2008)", 50.00);
    bookList.add(book);
    BasketDiscountPrice basketDiscountPrice = bookDiscountServiceImpl.calculateDiscount(bookList);

    assertEquals(new BasketDiscountPrice(50.00,1), basketDiscountPrice);

  }
  @Test
  public void shouldThrowEmptyBasketExceptionForEmptyBasket() throws Exception {

    Assertions.assertThrows(EmptyBasketException.class, () -> {
      List<Book> bookList = new ArrayList<Book>();
      Object discount = bookDiscountServiceImpl.calculateDiscount(bookList);
    });

  }

  @Test
  public void shouldReturnTheNumberOfDifferentBooksInABasket() throws Exception {
    List<Book> bookList = new ArrayList<Book>();
    Book book1 = new Book("Clean Code (Robert Martin, 2008)", 50.00);
    Book book2 = new Book("The Clean Coder (Robert Martin, 2011)", 50.00);
    Book book3 = new Book("Clean Architecture (Robert Martin, 2017)", 50.00);
    Book book4 = new Book("Test Driven Development by Example (Kent Beck, 2003)", 50.00);
    Book book5 = new Book("Working Effectively With Legacy Code (Michael C. Feathers, 2004)", 50.00);
    Book book6 = new Book("Clean Code (Robert Martin, 2008)", 50.00);
    Book book7 = new Book("The Clean Coder (Robert Martin, 2011)", 50.00);
    Book book8 = new Book("Clean Architecture (Robert Martin, 2017)", 50.00);
    Book book9 = new Book("Test Driven Development by Example (Kent Beck, 2003)", 50.00);
    bookList.add(book1);
    bookList.add(book2);
    bookList.add(book3);
    bookList.add(book4);
    bookList.add(book5);
    bookList.add(book6);
    bookList.add(book7);
    bookList.add(book8);
    bookList.add(book9);
    int numberOfDifferentBooks = bookDiscountServiceImpl.calculateNumberOfDifferentBooks(bookList);

    assertEquals(5, numberOfDifferentBooks);
  }
  /* Test for applying  5 % discount for only two different Books in the basket
  *  Rule = 2 different Books in the basket list of Books ==> apply  5 % discount on the TotalBasketPrice :)
  * */
  @Test
  public void shouldApply5percentDiscountForTwoDifferentBooksInBasket() throws Exception {
    List<Book> bookList = new ArrayList<Book>();
    Book book1 = new Book("Clean Code (Robert Martin, 2008)", 50.00);
    Book book2 = new Book("The Clean Coder (Robert Martin, 2011)", 50.00);
    Book book3 = new Book("Clean Code (Robert Martin, 2008)", 50.00);
    Book book4 = new Book("Clean Code (Robert Martin, 2008)", 50.00);
    Book book5 = new Book("The Clean Coder (Robert Martin, 2011)", 50.00);
    Book book6 = new Book("The Clean Coder (Robert Martin, 2011)", 50.00);
    Book book7 = new Book("Clean Code (Robert Martin, 2008)", 50.00);
    Book book8 = new Book("The Clean Coder (Robert Martin, 2011)", 50.00);
    Book book9 = new Book("Clean Code (Robert Martin, 2008)", 50.00);
    bookList.add(book1);
    bookList.add(book2);
    bookList.add(book3);
    bookList.add(book4);
    bookList.add(book5);
    bookList.add(book6);
    bookList.add(book7);
    bookList.add(book8);
    bookList.add(book9);
    BasketDiscountPrice basketDiscountPrice = bookDiscountServiceImpl.calculateDiscount(bookList);

    assertEquals(new BasketDiscountPrice(427.5,9), basketDiscountPrice);
  }

  /* Test for applying  10 % discount for only Three different Books in the basket
   *  Rule = 3 different Books in the basket list of Books ==> apply  10 % discount on the TotalBasketPrice :)
   * */
  @Test
  public void shouldApply10percentDiscountForThreeDifferentBooksInBasket() throws Exception {
    List<Book> bookList = new ArrayList<Book>();
    Book book1 = new Book("Clean Code (Robert Martin, 2008)", 50.00);
    Book book2 = new Book("The Clean Coder (Robert Martin, 2011)", 50.00);
    Book book3 = new Book("Clean Architecture (Robert Martin, 2017)", 50.00);
    Book book4 = new Book("Clean Code (Robert Martin, 2008)", 50.00);
    Book book5 = new Book("Clean Architecture (Robert Martin, 2017)", 50.00);
    Book book6 = new Book("The Clean Coder (Robert Martin, 2011)", 50.00);
    Book book7 = new Book("Clean Architecture (Robert Martin, 2017)", 50.00);
    Book book8 = new Book("The Clean Coder (Robert Martin, 2011)", 50.00);
    Book book9 = new Book("Clean Architecture (Robert Martin, 2017)", 50.00);
    bookList.add(book1);
    bookList.add(book2);
    bookList.add(book3);
    bookList.add(book4);
    bookList.add(book5);
    bookList.add(book6);
    bookList.add(book7);
    bookList.add(book8);
    bookList.add(book9);
    BasketDiscountPrice basketDiscountPrice = bookDiscountServiceImpl.calculateDiscount(bookList);

    assertEquals(new BasketDiscountPrice(405.00,9), basketDiscountPrice);
  }

  /* Test for applying  20 % discount for only Three different Books in the basket
   *  Rule = 4 different Books in the basket list of Books ==> apply  20 % discount on the TotalBasketPrice :)
   * */
  @Test
  public void shouldApply20percentDiscountForFourDifferentBooksInBasket() throws Exception {
    List<Book> bookList = new ArrayList<Book>();
    Book book1 = new Book("Clean Code (Robert Martin, 2008)", 50.00);
    Book book2 = new Book("The Clean Coder (Robert Martin, 2011)", 50.00);
    Book book3 = new Book("Clean Architecture (Robert Martin, 2017)", 50.00);
    Book book4 = new Book("Test Driven Development by Example (Kent Beck, 2003)", 50.00);
    Book book5 = new Book("Test Driven Development by Example (Kent Beck, 2003)", 50.00);
    Book book6 = new Book("The Clean Coder (Robert Martin, 2011)", 50.00);
    Book book7 = new Book("Clean Architecture (Robert Martin, 2017)", 50.00);
    Book book8 = new Book("Test Driven Development by Example (Kent Beck, 2003)", 50.00);
    Book book9 = new Book("Clean Architecture (Robert Martin, 2017)", 50.00);
    bookList.add(book1);
    bookList.add(book2);
    bookList.add(book3);
    bookList.add(book4);
    bookList.add(book5);
    bookList.add(book6);
    bookList.add(book7);
    bookList.add(book8);
    bookList.add(book9);
    BasketDiscountPrice basketDiscountPrice = bookDiscountServiceImpl.calculateDiscount(bookList);

    assertEquals(new BasketDiscountPrice(360.00,9), basketDiscountPrice);
  }

  /* Test for applying  25 % discount for only Three different Books in the basket
   *  Rule = 5 different Books in the basket list of Books ==> apply  25 % discount on the TotalBasketPrice :)
   * */

  @Test
  public void shouldApply25percentDiscountForFourDifferentBooksInBasket() throws Exception {
    List<Book> bookList = new ArrayList<Book>();
    Book book1 = new Book("Clean Code (Robert Martin, 2008)", 50.00);
    Book book2 = new Book("The Clean Coder (Robert Martin, 2011)", 50.00);
    Book book3 = new Book("Clean Architecture (Robert Martin, 2017)", 50.00);
    Book book4 = new Book("Test Driven Development by Example (Kent Beck, 2003)", 50.00);
    Book book5 = new Book("Working Effectively With Legacy Code (Michael C. Feathers, 2004)", 50.00);
    Book book6 = new Book("The Clean Coder (Robert Martin, 2011)", 50.00);
    Book book7 = new Book("Clean Architecture (Robert Martin, 2017)", 50.00);
    Book book8 = new Book("Test Driven Development by Example (Kent Beck, 2003)", 50.00);
    Book book9 = new Book("Working Effectively With Legacy Code (Michael C. Feathers, 2004)", 50.00);
    bookList.add(book1);
    bookList.add(book2);
    bookList.add(book3);
    bookList.add(book4);
    bookList.add(book5);
    bookList.add(book6);
    bookList.add(book7);
    bookList.add(book8);
    bookList.add(book9);
    BasketDiscountPrice basketDiscountPrice = bookDiscountServiceImpl.calculateDiscount(bookList);

    assertEquals(new BasketDiscountPrice(337.5,9), basketDiscountPrice);
  }

  /* Test for applying  10% discount on the three different Books of a Four Books type basket
   *  Rule = three different Books of a Four Books type basket ==> apply  10 % discount on the 3 different Books
   *                                                                + the fourth Book price=50.00 £ :)
   * */

  @Test
  public void shouldApply10percentDiscountForThreeDifferentBooksInBasketOf4Books() throws Exception {
    List<Book> bookList = new ArrayList<Book>();
    Book book1 = new Book("Clean Code (Robert Martin, 2008)", 50.00);
    Book book2 = new Book("The Clean Coder (Robert Martin, 2011)", 50.00);
    Book book3 = new Book("Clean Architecture (Robert Martin, 2017)", 50.00);
    Book book4 = new Book("Clean Code (Robert Martin, 2008)", 50.00);
    bookList.add(book1);
    bookList.add(book2);
    bookList.add(book3);
    bookList.add(book4);
    BasketDiscountPrice basketDiscountPrice = bookDiscountServiceImpl.calculateDiscount(bookList);

    assertEquals(new BasketDiscountPrice(185.00,4), basketDiscountPrice);
  }


}
