package com.target.product.api.exceptionhandling;

import com.target.product.api.exceptions.FeignClientException;
import com.target.product.api.exceptions.NoRecordFoundException;
import com.target.product.api.models.ErrorDetails;
import com.target.product.api.exceptions.InvalidCurrencyException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ProductApiGlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NoRecordFoundException.class)
    public ResponseEntity<ErrorDetails> handleNoRecordFoundException(NoRecordFoundException ex) {
        return ResponseEntity.status(404).body(new ErrorDetails(45656, ex.getMessage()));
    }

    @ExceptionHandler(FeignClientException.class)
    public ResponseEntity<ErrorDetails> handleFeignClientException(FeignClientException ex) {
        return ResponseEntity.status(503).body(new ErrorDetails(123456, ex.getMessage()));
    }

    @ExceptionHandler(InvalidCurrencyException.class)
    public ResponseEntity<ErrorDetails> handleInvalidCurrencyException(InvalidCurrencyException ex) {
        return ResponseEntity.status(503).body(new ErrorDetails(9999, ex.getMessage()));
    }

}
