package com.target.product.api.integration.feign;

import com.target.product.api.exceptions.NoRecordFoundException;
import com.target.product.api.exceptions.FeignClientException;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.http.HttpStatus;

public class FeignErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String s, Response response) {
        if(HttpStatus.NOT_FOUND.value() == response.status()){
            return new NoRecordFoundException();
        }
        return new FeignClientException(response.reason());
    }
}
