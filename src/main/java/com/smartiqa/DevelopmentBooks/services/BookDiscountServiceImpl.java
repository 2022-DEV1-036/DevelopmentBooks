package com.smartiqa.DevelopmentBooks.services;

import com.smartiqa.DevelopmentBooks.exceptions.EmptyBasketException;
import com.smartiqa.DevelopmentBooks.models.BasketDiscountPrice;
import com.smartiqa.DevelopmentBooks.models.Book;
import org.springframework.stereotype.Service;

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
    switch (numberOfDifferentBooks) {
      case 0:
        throw new EmptyBasketException("Your basket is empty !");
      case 1:
        basketDiscountPrice = new BasketDiscountPrice(bookList.get(0).getBookPrice(), numberOfBooks);
        break;
      case 2:
        Double totalAmount = (numberOfBooks * bookList.get(0).getBookPrice());
        Double totalDiscountAmount = totalAmount - (totalAmount * 5 / 100);
        basketDiscountPrice.setBasketAmount(totalDiscountAmount);
        basketDiscountPrice.setNumberOfArticles(numberOfBooks);
        break;
      case 3:
        Double totalAmount3 = (numberOfBooks * bookList.get(0).getBookPrice());
        Double totalDiscountAmount3 = totalAmount3 - (totalAmount3 * 10 / 100);
        basketDiscountPrice.setBasketAmount(totalDiscountAmount3);
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

  public static <T> Predicate<T> distinctBooksByName(
    Function<? super T, ?> keyExtractor) {
    Map<Object, Boolean> seen = new ConcurrentHashMap<>();
    return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
  }
}
