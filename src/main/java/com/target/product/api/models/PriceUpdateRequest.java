package com.target.product.api.models;

import com.fasterxml.jackson.annotation.JsonProperty;
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
public class PriceUpdateRequest implements Mappable<PriceUpdateRequest> {

    @JsonProperty("new_current_price")
    private BigDecimal value;
    private Currency currency;

    public <R> R mapTo(Function<PriceUpdateRequest,R> mappingFunction){
        return mappingFunction.apply(this);
    }

}
