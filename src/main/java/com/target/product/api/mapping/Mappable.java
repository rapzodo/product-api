package com.target.product.api.mapping;

import java.util.function.Function;

public interface Mappable<T> {

    <R> R mapTo(Function<T, R> mappingFunction);

}
