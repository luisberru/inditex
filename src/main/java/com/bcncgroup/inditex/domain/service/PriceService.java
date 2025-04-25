package com.bcncgroup.inditex.domain.service;

import org.springframework.stereotype.Service;
import com.bcncgroup.inditex.domain.exception.PriceNotFoundException;
import com.bcncgroup.inditex.domain.model.Price;
import com.bcncgroup.inditex.domain.port.inbound.GetPriceUseCase;
import com.bcncgroup.inditex.domain.port.outbound.PriceRepository;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class PriceService implements GetPriceUseCase {

	private static final Logger logger = LoggerFactory.getLogger(PriceService.class);

	private final PriceRepository priceRepository;

	public PriceService(PriceRepository priceRepository) {
		this.priceRepository = priceRepository;
	}

	@Override
	public Optional<Price> findApplicable(LocalDateTime date, Long productId, Long brandId) {
		logRequestDetails(date, productId, brandId);
		return priceRepository.findApplicable(date, productId, brandId).stream()
				.max(Comparator.comparingInt(Price::priority));
	}

	// helper si prefieres lanzar excepción directamente
	public Price getOrThrow(LocalDateTime date, Long productId, Long brandId) {
		return findApplicable(date, productId, brandId).orElseThrow(() -> new PriceNotFoundException(
				"No se encontró un precio para el producto %d de la marca %d en la fecha %s.".formatted(productId,
						brandId, date)));
	}

	private void logRequestDetails(LocalDateTime date, Long productId, Long brandId) {
		logger.info("Recibiendo solicitud para buscar precio con:");
		logger.info("Fecha: {}", date);
		logger.info("ID de Producto: {}", productId);
		logger.info("ID de Marca: {}", brandId);
	}

}
