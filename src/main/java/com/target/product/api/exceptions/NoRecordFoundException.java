package com.target.product.api.exceptions;

public class NoRecordFoundException extends RuntimeException {

    public NoRecordFoundException(String givenId) {
        super("No result found for the id : " + givenId);
    }

    public NoRecordFoundException() {
        super("No record found for given id");
    }
}
