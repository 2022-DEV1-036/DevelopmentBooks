package com.smartiqa.DevelopmentBooks.services;

import com.smartiqa.DevelopmentBooks.models.Book;

import java.util.List;

public interface BookDiscountService {
  public Object calculateDiscount(List<Book> bookList);
  public int calculateNumberOfDifferentBooks(List<Book> bookList) ;
  }
