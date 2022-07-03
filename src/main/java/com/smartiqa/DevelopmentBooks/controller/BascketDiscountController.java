package com.smartiqa.DevelopmentBooks.controller;

import com.smartiqa.DevelopmentBooks.models.BasketDiscountPrice;
import com.smartiqa.DevelopmentBooks.models.Book;
import com.smartiqa.DevelopmentBooks.services.BookDiscountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@Validated
public class BascketDiscountController {

  @Autowired
  BookDiscountServiceImpl bookDiscountService;

  @PostMapping("/api/v1/discount")
  public ResponseEntity<BasketDiscountPrice> getDiscountPrice (@RequestBody @Valid  List<Book> listBooks) {
    BasketDiscountPrice basketDiscountPrice = bookDiscountService.calculateDiscount(listBooks);
    return new ResponseEntity<>(basketDiscountPrice,HttpStatus.OK);
  }
}
