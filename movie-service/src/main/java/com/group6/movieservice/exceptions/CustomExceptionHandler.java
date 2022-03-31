/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group6.movieservice.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.nio.file.AccessDeniedException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@ControllerAdvice
public class CustomExceptionHandler {
 @ExceptionHandler(AccessDeniedException.class)
 public ResponseEntity<ErrorResponse> accessDeniedExceptionHandler(AccessDeniedException ex, WebRequest request) {
  ErrorResponse errorResponse = new ErrorResponse("access_denied", ex.getLocalizedMessage());
  return new ResponseEntity<>(errorResponse, HttpStatus.FORBIDDEN);
 }

 @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
 public ResponseEntity<ErrorResponse> methodNotSupportedExceptionHandler(HttpRequestMethodNotSupportedException ex, WebRequest request) {
  ErrorResponse errorResponse = new ErrorResponse("method_not_allowed", ". Use " + Objects.requireNonNull(ex.getSupportedMethods())[0]);
  return new ResponseEntity<>(errorResponse, HttpStatus.METHOD_NOT_ALLOWED);
 }

 @ExceptionHandler(MethodArgumentNotValidException.class)
 public ResponseEntity<ErrorResponse> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex, WebRequest request) {
  List<ValidationErrorResponse> validationErrors = new ArrayList<>();
  for (FieldError e : ex.getBindingResult().getFieldErrors()){
   validationErrors.add(new ValidationErrorResponse(e.getField(), e.getDefaultMessage()));
  }
  ErrorResponse errorResponse = new ErrorResponse("validation_error",  "Please ensure you filled all fields properly and with appropriate data", validationErrors);
  return new ResponseEntity<>(errorResponse, HttpStatus.UNPROCESSABLE_ENTITY);
 }

 @ExceptionHandler(MovieNotFoundException.class)
 public final ResponseEntity<ErrorResponse> notFoundExceptionHandler(MovieNotFoundException ex, WebRequest request) {
  ErrorResponse error = new ErrorResponse("not_found", ex.getLocalizedMessage());
  return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
 }

 @ExceptionHandler(Exception.class)
 public final ResponseEntity<ErrorResponse> handleAllExceptions(Exception ex, WebRequest request) {
  System.out.println(ex.getLocalizedMessage());
  ErrorResponse error = new ErrorResponse("server_error", ex.getLocalizedMessage() == null? "Oops! We messed up :( please try again later" : ex.getLocalizedMessage());
  return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
 }
}