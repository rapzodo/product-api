package com.target.product.api.domains;

import com.target.product.api.enums.Currency;
import com.target.product.api.mapping.Mappable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.function.Function;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CurrentPrice implements Mappable<CurrentPrice> {

    private BigDecimal value;
    private Currency currency;

    @Override
    public <R> R mapTo(Function<CurrentPrice, R> mappingFunction) {
        return mappingFunction.apply(this);
    }
}
