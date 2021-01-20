package com.target.product.api.integration.feign;

import com.target.product.api.domains.Product;
import feign.hystrix.FallbackFactory;
import lombok.AllArgsConstructor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "products-internal-service", url = "https://redsky.target.com/v3/pdp/")
public interface ProductsInternalServiceFeignClient extends ProductsInternalServiceClient {

    String GET_BY_TCIN_URL = "tcin/{id}?excludes=taxonomy,price,promotion,bulk_ship,rating_and_review_reviews,rating_and_review_statistics,question_answer_statistics&key=candidate";

    @GetMapping(ProductsInternalServiceFeignClient.GET_BY_TCIN_URL)
    Product getGetByTcinUrl(@PathVariable String id);

    @Component
    class ProductsInternalFallbackFactory implements FallbackFactory<ProductsInternalFallback> {

        @Override
        public ProductsInternalFallback create(Throwable throwable) {
            return new ProductsInternalFallback(throwable);
        }
    }

    @AllArgsConstructor
    class ProductsInternalFallback implements ProductsInternalServiceClient {

        private Throwable error;

        @Override
        public Product getGetByTcinUrl(String id) {
            return new Product();
        }
    }

}
