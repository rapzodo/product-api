package com.target.product.api.rest;

import com.target.product.api.domains.CurrentPrice;
import com.target.product.api.domains.Product;
import com.target.product.api.enums.Currency;
import io.restassured.RestAssured;
import io.restassured.filter.log.LogDetail;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;
import java.math.BigDecimal;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("local")
public class ProductControllerTest {

    private static final String GET_BY_ID_URL = "/products/{id}";
    public static final String EXAMPLE_PROD_NAME = "The Big Lebowski (Blu-ray)";
    @Resource
    private WebApplicationContext context;
    private static final String EXAMPLE_PRODUCT_ID = "13860428";

    @BeforeEach
    public void setup() {
        RestAssuredMockMvc.webAppContextSetup(context);
    }

    @Test
    public void shouldReturnSingleProduct() {
        final Product actualProduct = RestAssured.given()
                .port(6070)
                .when()
                .get(GET_BY_ID_URL, EXAMPLE_PRODUCT_ID).then()
                .statusCode(200)
                .and().log().ifValidationFails(LogDetail.ALL)
                .extract().as(Product.class);
        Assertions.assertThat(actualProduct).isEqualToComparingFieldByField(Product.builder()
                .productId(EXAMPLE_PRODUCT_ID)
                .productName(EXAMPLE_PROD_NAME)
                .currentPrice(CurrentPrice.builder().value(BigDecimal.valueOf(14.00)).currency(Currency.USD).build())
                .build());
    }

    @Test
    public void shouldReturnNotFoundCodeIfProductDoesNotExists() {
        RestAssured.given()
                .port(6070)
                .when()
                .get(GET_BY_ID_URL, "invalidId").then()
                .statusCode(404)
                .and().log().ifValidationFails(LogDetail.ALL);
    }


}
