package com.smartiqa.DevelopmentBooks.Controller;

import com.smartiqa.DevelopmentBooks.models.Book;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BascketDiscountController {

  @PostMapping("/api/v1/discount")
  Double  getDiscountPrice (@RequestBody List<Book> listBooks) {
    return 0.0;
  }
}
