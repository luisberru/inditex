package com.bcncgroup.inditex.adapter.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bcncgroup.inditex.adapter.controller.dto.PriceResponseDto;
import com.bcncgroup.inditex.application.service.PriceService;
import com.bcncgroup.inditex.domain.model.Price;

@RestController
@RequestMapping("/prices")
public class PriceController {

    private final PriceService priceService;

    public PriceController(PriceService priceService) {
        this.priceService = priceService;
    }

    @GetMapping
    public ResponseEntity<PriceResponseDto> getPrice(
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date,
            @RequestParam("productId") Long productId,
            @RequestParam("brandId") Long brandId) {
        Price price = priceService.getPrice(date, productId, brandId);
        PriceResponseDto response = PriceResponseDto.from(price);
        return ResponseEntity.ok(response);
    }
    

}
