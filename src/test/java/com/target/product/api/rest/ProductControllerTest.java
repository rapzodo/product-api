package com.target.product.api.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.filter.log.LogDetail;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("local")
public class ProductControllerTest {

    private static final String GET_BY_ID_URL = "/products/{id}";
    @Resource
    private WebApplicationContext context;
    private static final String EXAMPLE_PRODUCT_ID = "13860428";

    @BeforeEach
    public void setup() {
        RestAssuredMockMvc.webAppContextSetup(context);
    }

    @Test
    public void shouldReturnSingleProduct() {
        final String responseJson = RestAssured.given()
                .port(6070)
                .when()
                .get(GET_BY_ID_URL, EXAMPLE_PRODUCT_ID).then()
                .statusCode(200)
                .and().log().ifValidationFails(LogDetail.ALL)
                .extract().asString();
        Assertions.assertThat(responseJson).isEqualTo("{\n" +
                "  \"id\" : \"13860428\",\n" +
                "  \"name\" : \"The Big Lebowski (Blu-ray)\",\n" +
                "  \"current_price\" : {\n" +
                "    \"value\" : 14.0,\n" +
                "    \"currency\" : \"USD\"\n" +
                "  }\n" +
                "}");
    }

    @Test
    public void shouldReturnNotFoundCodeIfProductDoesNotExists() {
        RestAssured.given()
                .port(6070)
                .when()
                .get(GET_BY_ID_URL, "invalidId  ").then()
                .statusCode(404)
                .and().log().ifValidationFails(LogDetail.ALL);
    }


}
