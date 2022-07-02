package com.smartiqa.DevelopmentBooks.controller;

import com.smartiqa.DevelopmentBooks.models.BasketDiscoutPrice;
import com.smartiqa.DevelopmentBooks.models.Book;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
public class BascketDiscountController {

  @PostMapping("/api/v1/discount")
  public ResponseEntity<BasketDiscoutPrice> getDiscountPrice (@RequestBody List<Book> listBooks) {
    BasketDiscoutPrice basketDiscoutPrice = new BasketDiscoutPrice(0.0,0);
    return new ResponseEntity<BasketDiscoutPrice>(basketDiscoutPrice,HttpStatus.OK);
  }
}
