package com.target.product.api.services;

import com.target.product.api.exceptions.NoRecordFoundException;
import com.target.product.api.mapping.MappingFunctionFactory;
import com.target.product.api.domains.CurrentPrice;
import com.target.product.api.persistence.repositories.PriceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class DefaultCurrentPriceService implements CurrentPriceService {

    private final PriceRepository priceRepository;

    @Override
    public CurrentPrice getProductPriceByProduct(String productId) {
        log.info("searching for productId: {}", productId);
        return priceRepository.findByProductId(productId)
                .map(MappingFunctionFactory.productPriceToCurrentPrice())
                .orElseThrow(() -> new NoRecordFoundException(productId));
    }

    @Override
    public CurrentPrice updateProductPrice(String productId, CurrentPrice newCurrentPrice) {
        log.info("updating price for product id: {} with new value :{}", productId, newCurrentPrice.getValue());
        return priceRepository.findByProductId(productId)
                .map(currentPrice -> {
                    currentPrice.setValue(newCurrentPrice.getValue());
                    currentPrice.setCurrencyCode(newCurrentPrice.getCurrency().getCode());
                    return priceRepository.save(currentPrice);
                }).get()
                .mapTo(MappingFunctionFactory.productPriceToCurrentPrice());
    }

}
