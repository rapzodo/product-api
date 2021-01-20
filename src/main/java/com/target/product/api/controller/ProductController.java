package com.target.product.api.controller;

import com.target.product.api.domains.CurrentPrice;
import com.target.product.api.domains.Product;
import com.target.product.api.models.PriceUpdateRequest;
import com.target.product.api.services.DefaultProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api
@RestController
@RequestMapping("products")
@RequiredArgsConstructor
public class ProductController {

    private final DefaultProductService defaultProductService;

    @GetMapping("{id}")
    @ApiOperation(value = "get product details given the product id")
    private ResponseEntity<Product> getProduct(@PathVariable String id) {
        return ResponseEntity.ok(defaultProductService.getProduct(id));
    }

    @PutMapping("{id}")
    @ApiOperation(value = "Updates the current price for a given product Id")
    public ResponseEntity<CurrentPrice> updateProductPrice(@PathVariable String id, @RequestBody PriceUpdateRequest priceUpdateRequest) {
        return ResponseEntity.ok(defaultProductService.updateCurrentPrice(id, priceUpdateRequest));
    }
}
