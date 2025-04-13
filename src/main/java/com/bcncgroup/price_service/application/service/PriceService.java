package com.bcncgroup.price_service.application.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.bcncgroup.price_service.domain.model.Price;
import com.bcncgroup.price_service.infrastructure.exception.PriceNotFoundException;
import com.bcncgroup.price_service.infrastructure.repository.PriceRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class PriceService {

    private static final Logger logger = LoggerFactory.getLogger(PriceService.class);

    private final PriceRepository priceRepository;

    public PriceService(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    public Price getPrice(LocalDateTime date, Long productId, Long brandId) {
        logger.info("Recibiendo solicitud para buscar precio con:");
        logger.info("Fecha: {}", date);
        logger.info("ID de Producto: {}", productId);
        logger.info("ID de Marca: {}", brandId);

        return priceRepository.findApplicablePrice(date, productId, brandId)
            .orElseThrow(() -> new PriceNotFoundException("Precio no encontrado"));
    }
    
    public List<Map<String, Object>> getAllProductDates() {
        List<Price> prices = priceRepository.findAll();
        List<Map<String, Object>> result = new ArrayList<>();

        for (Price price : prices) {
            Map<String, Object> item = new HashMap<>();
            item.put("productId", price.getProductId());
            item.put("brandId", price.getBrandId());
            item.put("startDate", price.getStartDate());
            item.put("endDate", price.getEndDate());
            item.put("price", price.getPrice());
            result.add(item);
        }

        return result;
    }



}

