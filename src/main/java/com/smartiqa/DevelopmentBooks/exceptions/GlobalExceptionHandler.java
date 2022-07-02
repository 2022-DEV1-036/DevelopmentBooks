package com.smartiqa.DevelopmentBooks.exceptions;

import com.smartiqa.DevelopmentBooks.models.ErrorObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(EmptyBasketException.class)
  public ResponseEntity<ErrorObject> handleEmptyBasketException(EmptyBasketException emptyBasketException, WebRequest request){
      ErrorObject errorObject = new ErrorObject();
      errorObject.setStatusCode(HttpStatus.BAD_REQUEST.value());
      errorObject.setMessage(emptyBasketException.getMessage());
      errorObject.setTimeStamp(new Date());

      return  new ResponseEntity<ErrorObject>(errorObject,HttpStatus.BAD_REQUEST);
  }
}
