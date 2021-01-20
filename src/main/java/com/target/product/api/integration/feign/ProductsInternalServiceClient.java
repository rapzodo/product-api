package com.target.product.api.integration.feign;

import com.target.product.api.domains.Product;
import org.springframework.web.bind.annotation.PathVariable;

public interface ProductsInternalServiceClient {

    Product getGetByTcinUrl(@PathVariable String id);
}
