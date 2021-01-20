package com.target.product.api.domains;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {

    @JsonProperty("id")
    private String productId;
    @JsonProperty("name")
    private String productName;
    @JsonProperty("current_price")
    private CurrentPrice currentPrice;

    @JsonProperty("product")
    private void mapProductIdAndName(Map<String, Object> productMap) {
        Map<String, Object> item = (Map<String, Object>) productMap.get("item");
        productId = (String) item.get("tcin");
        Map<String, Object> productDescription = (Map<String, Object>) item.get("product_description");
        productName = (String) productDescription.get("title");
    }

}
