package com.smartiqa.DevelopmentBooks.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BasketDiscoutPrice {

  private Double basketAmount ;
  private int numberOfArticles ;

}
