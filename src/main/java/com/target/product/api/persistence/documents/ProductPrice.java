package com.target.product.api.persistence.documents;

import com.target.product.api.mapping.Mappable;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.function.Function;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document
public class ProductPrice implements Mappable<ProductPrice> {

    @Id
    private String id;
    @Indexed(unique = true)
    @TextIndexed
    private String productId;
    private BigDecimal value;
    private String currencyCode;

    @Override
    public <R> R mapTo(Function<ProductPrice, R> mappingFunction) {
        return mappingFunction.apply(this);
    }

}
