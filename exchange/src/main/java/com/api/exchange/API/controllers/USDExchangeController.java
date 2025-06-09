package com.api.exchange.API.controllers;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.exchange.Bussiness.Services.USDExchangeService;
import com.api.exchange.Domain.dtos.USDExchangeRateDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/exchange-rate")
public class USDExchangeController {
    private final USDExchangeService usdExchangeService;

    @GetMapping(value = "/CRC-USD")
    public ResponseEntity<USDExchangeRateDto> getExchangeRate(
        @RequestParam(name = "date", required = false) @DateTimeFormat(pattern = "dd/MM/yyyy") Date date
    ) {
        return usdExchangeService.getExchangeRate(date).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity
                        .status(503)
                        .build());
    }
}
