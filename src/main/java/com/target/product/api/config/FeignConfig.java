package com.target.product.api.config;

import com.target.product.api.integration.feign.FeignErrorDecoder;
import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {

    @Bean
    public FeignErrorDecoder getFeignErrorDecoder() {
        return new FeignErrorDecoder();
    }

    //logger for feign clients
    @Bean
    public Logger.Level getLoggerLevel() {
        return Logger.Level.FULL;
    }

}
