package com.target.product.api.integration;

import com.target.product.api.integration.feign.ProductsInternalServiceClient;
import com.target.product.api.domains.Product;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Log4j2
public class ProductsInternalServiceRestTemplateClient implements ProductsInternalServiceClient {

    String GET_BY_URL = "https://redsky.target.com/v3/pdp/tcin/{id}?excludes=taxonomy,price,promotion,bulk_ship,rating_and_review_reviews,rating_and_review_statistics,question_answer_statistics&key=candidate";

    public Product getGetByTcinUrl(String id) {
        log.info("Fetching the product");
        return new RestTemplate().getForObject(GET_BY_URL, Product.class, id);
    }
}

