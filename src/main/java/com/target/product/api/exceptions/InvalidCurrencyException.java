package com.target.product.api.exceptions;

public class InvalidCurrencyException extends RuntimeException {
    public InvalidCurrencyException() {
        super("Invalid currency");
    }
}
