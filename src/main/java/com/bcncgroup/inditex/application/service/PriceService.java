package com.bcncgroup.inditex.application.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.bcncgroup.inditex.domain.model.Price;
import com.bcncgroup.inditex.infrastructure.exception.PriceNotFoundException;
import com.bcncgroup.inditex.infrastructure.repository.PriceRepository;

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

}

