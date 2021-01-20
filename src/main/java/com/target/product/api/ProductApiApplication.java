package com.target.product.api;

import com.target.product.api.enums.Currency;
import com.target.product.api.persistence.documents.ProductPrice;
import com.target.product.api.persistence.repositories.PriceRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import java.math.BigDecimal;
import java.util.Arrays;

@SpringBootApplication
@EnableFeignClients
public class ProductApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductApiApplication.class, args);
    }

    @Bean
    @Profile("local")
    public CommandLineRunner commandLineRunner(PriceRepository priceRepository) {
        return (a) -> {
            priceRepository.deleteAll();

            priceRepository.saveAll(Arrays.asList(
                    ProductPrice.builder()
                            .productId("13860428")
                            .value(BigDecimal.valueOf(14.00))
                            .currencyCode(Currency.USD.getCode())
                            .build(),
                    ProductPrice.builder()
                            .productId("54456119")
                            .value(BigDecimal.valueOf(21.00))
                            .currencyCode(Currency.USD.getCode())
                            .build(),
                    ProductPrice.builder()
                            .productId("13264003")
                            .value(BigDecimal.valueOf(299.22))
                            .currencyCode(Currency.USD.getCode())
                            .build(),
                    ProductPrice.builder()
                            .productId("12954218")
                            .value(BigDecimal.valueOf(3.89))
                            .currencyCode(Currency.USD.getCode())
                            .build())
            );
        };
    }
}
