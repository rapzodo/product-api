package com.target.product.api.mapping;

import com.target.product.api.domains.CurrentPrice;
import com.target.product.api.enums.Currency;
import com.target.product.api.models.PriceUpdateRequest;
import com.target.product.api.persistence.documents.ProductPrice;

import java.util.function.Function;

public class MappingFunctionFactory {

    public static Function<PriceUpdateRequest, CurrentPrice> priceUpdateToCurrentPrice() {
        return request ->
                CurrentPrice.builder()
                        .value(request.getValue())
                        .currency(request.getCurrency())
                        .build();
    }

    public static Function<ProductPrice, CurrentPrice> productPriceToCurrentPrice() {
        return productPrice ->
                CurrentPrice.builder()
                        .value(productPrice.getValue())
                        .currency(Currency.valueOf(productPrice.getCurrencyCode()))
                        .build();
    }
}
