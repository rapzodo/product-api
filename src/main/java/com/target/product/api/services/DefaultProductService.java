package com.target.product.api.services;

import com.target.product.api.domains.CurrentPrice;
import com.target.product.api.domains.Product;
import com.target.product.api.enums.Currency;
import com.target.product.api.integration.feign.ProductsInternalServiceClient;
import com.target.product.api.mapping.MappingFunctionFactory;
import com.target.product.api.models.PriceUpdateRequest;
import com.target.product.api.exceptions.InvalidCurrencyException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class DefaultProductService implements ProductService {

    private final CurrentPriceService priceService;
    private final ProductsInternalServiceClient productsInternalServiceFeignClient;

    @Override
    public Product getProduct(String id) {
        log.info("calling internal products service");
        final Product product = productsInternalServiceFeignClient.getGetByTcinUrl(id);
        log.info("product found : {}", product);
        product.setCurrentPrice(priceService.getProductPriceByProduct(id));
        log.info("current price for product : {} was : {}", id, product.getCurrentPrice());
        return product;
    }

    @Override
    public CurrentPrice updateCurrentPrice(String productId, PriceUpdateRequest priceUpdateRequest) {
        if (priceUpdateRequest.getCurrency() == Currency.UNKNOWN) {
            throw new InvalidCurrencyException();
        }
        return priceService.updateProductPrice(productId, priceUpdateRequest.mapTo(MappingFunctionFactory.priceUpdateToCurrentPrice()));
    }

}
