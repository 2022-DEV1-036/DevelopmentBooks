package com.smartiqa.DevelopmentBooks.services;

import com.smartiqa.DevelopmentBooks.models.BasketDiscountPrice;
import com.smartiqa.DevelopmentBooks.models.Book;

import java.util.List;

public interface BookDiscountService {
   BasketDiscountPrice calculateDiscount(List<Book> bookList);
   int calculateNumberOfDifferentBooks(List<Book> bookList) ;
   List<Book> getBooks();
}
