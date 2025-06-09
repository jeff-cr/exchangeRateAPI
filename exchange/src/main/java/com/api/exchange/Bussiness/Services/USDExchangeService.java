package com.api.exchange.Bussiness.Services;


import java.util.Date;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.api.exchange.API.config.ExchangeConfig;
import com.api.exchange.Domain.dtos.USDExchangeRateDto;
import com.api.exchange.Utilities.helpers.ExchangeRate;

@Service
public class USDExchangeService {
  
     private final ExchangeConfig harbestConfig;

    public USDExchangeService(ExchangeConfig harbestConfig) {
       this.harbestConfig = harbestConfig;
    }

    public Optional<USDExchangeRateDto> getExchangeRate(Date date) {
      
        try {
             ExchangeRate exchangeRate = new ExchangeRate(harbestConfig, date);
            var sellingRate =  exchangeRate.getVenta();
            var buyingRate = exchangeRate.getCompra();

            USDExchangeRateDto dto = new USDExchangeRateDto();
            dto.setBuyingRate(buyingRate);
            dto.setSellingRate(sellingRate);

            return Optional.of(dto);
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
