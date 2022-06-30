package com.smartiqa.DevelopmentBooks.services;

import com.smartiqa.DevelopmentBooks.models.Book;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class BookDiscountService {

  public Object calculateDiscount(List<Book> bookList) {
    int numberOfBooks = bookList.size();
    if (numberOfBooks == 0) {
      return "Your basket is empty !";
    }
    if (numberOfBooks == 1) {
      return bookList.get(0).getBookPrice();
    }
    return 0.0;
  }


}
