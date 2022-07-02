package com.smartiqa.DevelopmentBooks.exceptions;

public class EmptyBasketException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public EmptyBasketException (String message){
    super(message);

  }

}
