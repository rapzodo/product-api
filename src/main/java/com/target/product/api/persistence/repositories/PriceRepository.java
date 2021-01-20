package com.target.product.api.persistence.repositories;

import com.target.product.api.persistence.documents.ProductPrice;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface PriceRepository extends MongoRepository<ProductPrice, String> {

    Optional<ProductPrice> findByProductId(String productId);
}
