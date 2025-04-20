package com.bcncgroup.inditex.application.service;

import org.springframework.stereotype.Service;

import com.bcncgroup.inditex.adapter.controller.dto.PriceRequestDto;
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

    public Price getPrice(PriceRequestDto request) {
        logRequestDetails(request);
        return findApplicablePrice(request);
    }

    private void logRequestDetails(PriceRequestDto request) {
        logger.info("Recibiendo solicitud para buscar precio con:");
        logger.info("Fecha: {}", request.getDate());
        logger.info("ID de Producto: {}", request.getProductId());
        logger.info("ID de Marca: {}", request.getBrandId());
    }

    private Price findApplicablePrice(PriceRequestDto request) {
        return priceRepository.findApplicablePrice(request.getDate(), request.getProductId(), request.getBrandId())
                .orElseThrow(() -> new PriceNotFoundException("Precio no encontrado"));
    }

}

