package com.CPTM.stock.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalErrorHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<DefaultErrorMessage> handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
        var defaultErrorMessage = new DefaultErrorMessage(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(defaultErrorMessage);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<DefaultErrorMessage> handleResourceNotFound(ResourceNotFoundException e){
        var defaultErrorMessage = new DefaultErrorMessage(HttpStatus.NOT_FOUND.value(), e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(defaultErrorMessage);
    }

    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<DefaultErrorMessage> handleDuplicateResource(DuplicateResourceException e){
        var defaultErrorMessage = new DefaultErrorMessage(HttpStatus.CONFLICT.value(), e.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(defaultErrorMessage);
    }

    @ExceptionHandler(InvalidRequestException.class)
    public ResponseEntity<DefaultErrorMessage> handleInvalidRequestException(InvalidRequestException e){
        var defaultErrorMessage = new DefaultErrorMessage(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(defaultErrorMessage);
    }

    @ExceptionHandler(BusinessRuleException.class)
    public ResponseEntity<DefaultErrorMessage> handleBusinessRuleException(BusinessRuleException e){
        var defaultErrorMessage = new DefaultErrorMessage(HttpStatus.CONFLICT.value(), e.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(defaultErrorMessage);
    }
}
