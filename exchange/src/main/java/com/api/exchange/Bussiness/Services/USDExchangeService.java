package com.api.exchange.Bussiness.Services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.api.exchange.API.config.ExchangeConfig;
import com.api.exchange.Domain.dtos.USDExchangeRateDto;
import com.api.exchange.Utilities.helpers.ExchangeRate;

@Service
public class USDExchangeService {
    private ExchangeRate exchangeRate;

    public USDExchangeService(ExchangeConfig harbestConfig) {
        this.exchangeRate = new ExchangeRate(harbestConfig);
    }

    private double getBuyExchangeRate() {
        return exchangeRate.getCompra();
    }

    private double getSellExchangeRate() {
        return exchangeRate.getVenta();
    }

    public Optional<USDExchangeRateDto> getExchangeRate() {
        try {
            var sellingRate = getSellExchangeRate();
            var buyingRate = getBuyExchangeRate();

            USDExchangeRateDto dto = new USDExchangeRateDto();
            dto.setBuyingRate(buyingRate);
            dto.setSellingRate(sellingRate);

            return Optional.of(dto);
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
