package com.smartiqa.DevelopmentBooks;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smartiqa.DevelopmentBooks.models.Book;
import org.assertj.core.util.CanIgnoreReturnValue;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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


  @BeforeAll
  public static void setup() {
    request = new MockHttpServletRequest();
  }
  @Test
  @CanIgnoreReturnValue
  public void checkPostBooksResponseStatusCode () throws Exception {
    List<Book> bookList = new ArrayList<Book>();
    Book book = new Book("Clean Code (Robert Martin, 2008)",50.00);
    bookList.add(book);
    mockMvc.perform(post("/api/v1/discount")
      .contentType(MediaType.APPLICATION_JSON)
      .content(objectMapper.writeValueAsString(bookList)))
      .andExpect(status().isOk());


  }

}