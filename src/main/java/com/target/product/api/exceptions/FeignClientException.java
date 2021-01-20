package com.target.product.api.exceptions;

public class FeignClientException extends RuntimeException {

    public FeignClientException(String errorMessage) {
        super(errorMessage);
    }
}
