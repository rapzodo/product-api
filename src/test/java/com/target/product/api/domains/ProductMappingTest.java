package com.target.product.api.domains;


import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

public class ProductMappingTest {

    private ObjectMapper objectMapper;
    private File jsonFile;

    @BeforeEach
    public void setup() {
        objectMapper = new ObjectMapper()
                .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        jsonFile = new File("example.json");
    }

    @Test
    public void shouldMapProductId() throws IOException {
        Product product = objectMapper.readValue(jsonFile, Product.class);
        Assertions.assertThat(product.getProductId()).isEqualTo("13860428");
    }

    @Test
    public void shouldMapProductName() throws IOException {
        Product product = objectMapper.readValue(jsonFile, Product.class);
        Assertions.assertThat(product.getProductName()).isEqualTo("The Big Lebowski (Blu-ray)");
    }
}
