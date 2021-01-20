package com.target.product.api.services;

import com.target.product.api.domains.CurrentPrice;

public interface CurrentPriceService {

    CurrentPrice getProductPriceByProduct(String id);

    CurrentPrice updateProductPrice(String productId, CurrentPrice currentPrice);
}
