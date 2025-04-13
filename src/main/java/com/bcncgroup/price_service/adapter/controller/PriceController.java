package com.bcncgroup.price_service.adapter.controller;

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

import com.bcncgroup.price_service.adapter.controller.dto.PriceResponseDto;
import com.bcncgroup.price_service.application.service.PriceService;
import com.bcncgroup.price_service.domain.model.Price;

@RestController
@RequestMapping("/api")
public class PriceController {

    private final PriceService priceService;

    public PriceController(PriceService priceService) {
        this.priceService = priceService;
    }

    @GetMapping("/prices")
    public ResponseEntity<PriceResponseDto> getPrice(
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date,
            @RequestParam("productId") Long productId,
            @RequestParam("brandId") Long brandId) {
        Price price = priceService.getPrice(date, productId, brandId);
        PriceResponseDto response = PriceResponseDto.from(price);
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/now")
    public LocalDateTime getCurrentDateTime() {
        return LocalDateTime.now();
    }
    
    @GetMapping("/all-products-dates")
    public ResponseEntity<List<Map<String, Object>>> getAllProductDates() {
        List<Map<String, Object>> response = priceService.getAllProductDates();
        return ResponseEntity.ok(response);
    }

}
