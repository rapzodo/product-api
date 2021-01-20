package com.target.product.api.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum Currency {
    USD("USD", "$"), BRL("BRL", "R$"), UNKNOWN("UNKNOWN","");

    @JsonValue
    private String code;
    private String symbol;

    Currency(String code, String symbol) {
        this.code = code;
        this.symbol = symbol;
    }

    @JsonCreator
    public static Currency fromString(String code) {
        for (Currency c : Currency.values()) {
            if (code.equals(c.code)) {
                return c;
            }
        }
        return UNKNOWN;
    }
}
