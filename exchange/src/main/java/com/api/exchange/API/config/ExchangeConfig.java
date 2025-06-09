package com.api.exchange.API.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
@ConfigurationProperties
public class ExchangeConfig {

    private Bccr bccr;

    @Data
    public static final class Bccr {
        private String email;
        private String token;
    }
}
