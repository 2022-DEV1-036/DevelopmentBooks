package com.smartiqa.DevelopmentBooks.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BasketDiscountPrice {

  private Double basketAmount ;
  private int numberOfArticles ;

}
