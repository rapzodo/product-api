package com.target.product.api.services;

import com.target.product.api.domains.CurrentPrice;
import com.target.product.api.domains.Product;
import com.target.product.api.models.PriceUpdateRequest;

public interface ProductService {

    Product getProduct(String id);

    CurrentPrice updateCurrentPrice(String productId, PriceUpdateRequest build);
}
