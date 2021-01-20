package com.target.product.exceptions;

public class InvalidCurrencyException extends RuntimeException {
    public InvalidCurrencyException() {
        super("Invalid currency");
    }
}
