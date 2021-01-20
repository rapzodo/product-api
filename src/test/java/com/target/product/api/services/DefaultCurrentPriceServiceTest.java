package com.target.product.api.services;

import com.target.product.api.exceptions.NoRecordFoundException;
import com.target.product.api.domains.CurrentPrice;
import com.target.product.api.enums.Currency;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;

@SpringBootTest
@ActiveProfiles("local")
public class DefaultCurrentPriceServiceTest {

    @Autowired
    private DefaultCurrentPriceService service;

    @Test
    public void shouldUpdatePrice() {

        final String productId = "13860428";
        CurrentPrice currentPrice = service.getProductPriceByProduct(productId);
        Assertions.assertEquals(14.00, currentPrice.getValue().doubleValue());

        final Currency expectedNewCurrency = Currency.BRL;
        final BigDecimal expectedNewValue = BigDecimal.TEN;

        currentPrice.setValue(expectedNewValue);
        currentPrice.setCurrency(expectedNewCurrency);

        service.updateProductPrice(productId, currentPrice);

        currentPrice = service.getProductPriceByProduct(productId);
        Assertions.assertEquals(CurrentPrice.builder().value(expectedNewValue).currency(expectedNewCurrency).build(), currentPrice);
    }

    @Test
    public void shouldFindProductPriceGivenProductId() {
        final CurrentPrice productPrice = service.getProductPriceByProduct("13860428");
        Assertions.assertNotNull(productPrice);
    }

    @Test
    public void shouldThrowNoResultFoundExceptionIfThereIsResultForGivenProductId() {
        Assertions.assertThrows(NoRecordFoundException.class, () -> service.getProductPriceByProduct("invalidId"));
    }

}