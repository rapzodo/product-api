package com.target.product.api.services;

import com.target.product.api.domains.CurrentPrice;
import com.target.product.api.domains.Product;
import com.target.product.api.enums.Currency;
import com.target.product.api.integration.feign.ProductsInternalServiceClient;
import com.target.product.api.models.PriceUpdateRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

@ExtendWith(MockitoExtension.class)
public class DefaultProductServiceTest {

    @InjectMocks
    private DefaultProductService defaultProductService;
    @Mock
    private CurrentPriceService mockCurrentPriceService;
    @Mock
    private ProductsInternalServiceClient mockProductInternalService;
    @Captor
    private ArgumentCaptor<CurrentPrice> priceArgumentCaptor;

    @Test
    public void getProduct() {
        final Product expectedProduct = Product.builder()
                .productName("Test product")
                .build();
        Mockito.when(mockProductInternalService.getGetByTcinUrl(ArgumentMatchers.anyString()))
                .thenReturn(expectedProduct);
        Mockito.when(mockCurrentPriceService.getProductPriceByProduct(ArgumentMatchers.anyString()))
                .thenReturn(CurrentPrice.builder().value(BigDecimal.TEN).currency(Currency.USD).build());
        final Product actualProduct = defaultProductService.getProduct("1");
        Assertions.assertEquals(actualProduct, expectedProduct);
        Assertions.assertEquals(10.00, actualProduct.getCurrentPrice().getValue().doubleValue());
    }

    @Test
    public void shouldUpdatePrice() {
        final PriceUpdateRequest priceUpdateRequest = PriceUpdateRequest.builder()
                .value(BigDecimal.valueOf(49.99))
                .currency(Currency.BRL)
                .build();
        defaultProductService.updateCurrentPrice("1", priceUpdateRequest);
        Mockito.verify(mockCurrentPriceService)
                .updateProductPrice(ArgumentMatchers.anyString(), priceArgumentCaptor.capture());
        final CurrentPrice expectedPriceArgument = priceArgumentCaptor.getValue();
        Assertions.assertEquals(expectedPriceArgument.getValue(), priceUpdateRequest.getValue());
        Assertions.assertEquals(expectedPriceArgument.getCurrency(), priceUpdateRequest.getCurrency());
    }
}