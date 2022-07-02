package com.smartiqa.DevelopmentBooks.controller;

import com.smartiqa.DevelopmentBooks.models.BasketDiscountPrice;
import com.smartiqa.DevelopmentBooks.models.Book;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BascketDiscountController {

  @PostMapping("/api/v1/discount")
  public ResponseEntity<BasketDiscountPrice> getDiscountPrice (@RequestBody List<Book> listBooks) {
    BasketDiscountPrice basketDiscountPrice = new BasketDiscountPrice(0.0,0);
    return new ResponseEntity<BasketDiscountPrice>(basketDiscountPrice,HttpStatus.OK);
  }
}
