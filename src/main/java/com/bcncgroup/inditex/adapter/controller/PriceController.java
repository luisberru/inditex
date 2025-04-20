package com.bcncgroup.inditex.adapter.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bcncgroup.inditex.adapter.controller.dto.PriceRequestDto;
import com.bcncgroup.inditex.adapter.controller.dto.PriceResponseDto;
import com.bcncgroup.inditex.application.service.PriceService;
import com.bcncgroup.inditex.domain.model.Price;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/prices")
public class PriceController {

    private final PriceService priceService;

    public PriceController(PriceService priceService) {
        this.priceService = priceService;
    }

    @GetMapping
    public ResponseEntity<PriceResponseDto> getPrice(@Valid @ModelAttribute PriceRequestDto request) {
        Price price = priceService.getPrice(request);
        PriceResponseDto response = PriceResponseDto.from(price);
        return ResponseEntity.ok(response);
    }
    

}
