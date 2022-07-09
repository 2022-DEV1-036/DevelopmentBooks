package com.smartiqa.DevelopmentBooks.services;

import com.smartiqa.DevelopmentBooks.exceptions.EmptyBasketException;
import com.smartiqa.DevelopmentBooks.models.BasketDiscountPrice;
import com.smartiqa.DevelopmentBooks.models.Book;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class BookDiscountServiceImpl implements BookDiscountService {

  @Override
  public BasketDiscountPrice calculateDiscount(List<Book> bookList) {
    Integer numberOfBooks = bookList.size();
    BasketDiscountPrice basketDiscountPrice = new BasketDiscountPrice();
    int numberOfDifferentBooks = calculateNumberOfDifferentBooks(bookList);

    if (numberOfBooks==0){
      throw new EmptyBasketException("Your basket is empty !");
    }
    switch (numberOfDifferentBooks) {
      case 0:
        if (numberOfBooks!=0){
          basketDiscountPrice.setBasketAmount(calculateTotalBasketAmount(numberOfBooks, bookList.get(0).getBookPrice()));
          basketDiscountPrice.setNumberOfArticles(numberOfBooks);
        }
      case 1:
        basketDiscountPrice.setBasketAmount(
          calculateTotalBasketAmount(numberOfBooks, bookList.get(0).getBookPrice()));
          basketDiscountPrice.setNumberOfArticles(numberOfBooks);
        break;
      case 2:
        basketDiscountPrice.setBasketAmount(
          calculateTotalBasketAmount(
            numberOfBooks, bookList.get(0).getBookPrice())
            - (calculateTotalBasketAmount(numberOfBooks, bookList.get(0).getBookPrice() * 5 / 100))
        );
        basketDiscountPrice.setNumberOfArticles(numberOfBooks);
        break;
      case 3:
        if (numberOfBooks != 4) {
          basketDiscountPrice.setBasketAmount(
            calculateTotalBasketAmount(
              numberOfBooks, bookList.get(0).getBookPrice())
              - (calculateTotalBasketAmount(numberOfBooks, bookList.get(0).getBookPrice() * 10 / 100))
          );
          basketDiscountPrice.setNumberOfArticles(numberOfBooks);
        } else {
          basketDiscountPrice.setBasketAmount(
            calculateTotalBasketAmount(
              numberOfBooks, bookList.get(0).getBookPrice())
              - (calculateTotalBasketAmount(numberOfBooks - 1, bookList.get(0).getBookPrice() * 10 / 100))
          );
          basketDiscountPrice.setNumberOfArticles(numberOfBooks);
        }

        break;
      case 4:
        basketDiscountPrice.setBasketAmount(
          calculateTotalBasketAmount(
            numberOfBooks, bookList.get(0).getBookPrice())
            - (calculateTotalBasketAmount(numberOfBooks, bookList.get(0).getBookPrice() * 20 / 100))
        );
        basketDiscountPrice.setNumberOfArticles(numberOfBooks);
        break;
      case 5:
        basketDiscountPrice.setBasketAmount(
          calculateTotalBasketAmount(
            numberOfBooks, bookList.get(0).getBookPrice())
            - (calculateTotalBasketAmount(numberOfBooks, bookList.get(0).getBookPrice() * 25 / 100))
        );
        basketDiscountPrice.setNumberOfArticles(numberOfBooks);
        break;
      default:
        break;
    }
    return basketDiscountPrice;

  }

  @Override
  public int calculateNumberOfDifferentBooks(List<Book> bookList) {
    List<Book> listOfDistinctBooks = bookList.stream()
      .filter(distinctBooksByName(b -> b.getBookName()))
      .collect(Collectors.toList());
    System.out.println(listOfDistinctBooks);
    return listOfDistinctBooks.size();
  }

  @Override
  public List<Book> getBooks() {
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
    return bookList;
  }

  public static <T> Predicate<T> distinctBooksByName(
    Function<? super T, ?> keyExtractor) {
    Map<Object, Boolean> seen = new ConcurrentHashMap<>();
    return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
  }

  public Double calculateTotalBasketAmount(int numberOfBooks, Double bookPrice) {
    return numberOfBooks * bookPrice;
  }
}
