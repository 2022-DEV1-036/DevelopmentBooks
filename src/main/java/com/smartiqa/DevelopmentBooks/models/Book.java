package com.smartiqa.DevelopmentBooks.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Book {

  @NotEmpty(message = "Book name is required !")
  private String bookName ;
  @NotNull(message = "The Book Price is required !")
  private Double bookPrice ;
}
