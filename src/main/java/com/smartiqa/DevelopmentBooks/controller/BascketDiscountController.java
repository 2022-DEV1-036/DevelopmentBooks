package com.smartiqa.DevelopmentBooks.controller;

import com.smartiqa.DevelopmentBooks.models.BasketDiscountPrice;
import com.smartiqa.DevelopmentBooks.models.Book;
import com.smartiqa.DevelopmentBooks.services.BookDiscountServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Validated
public class BascketDiscountController {

  @Autowired
  BookDiscountServiceImpl bookDiscountService;

  @PostMapping("/discount")
  public ResponseEntity<BasketDiscountPrice> getDiscountPrice (@RequestBody @Valid  List<Book> listBooks) {
    BasketDiscountPrice basketDiscountPrice = bookDiscountService.calculateDiscount(listBooks);
    return new ResponseEntity<>(basketDiscountPrice,HttpStatus.OK);
  }

  @GetMapping("/books")
  public ResponseEntity<List<Book>> getBooks () {
   List<Book> bookList = bookDiscountService.getBooks();
    return new ResponseEntity<>(bookList,HttpStatus.OK);
  }
}
