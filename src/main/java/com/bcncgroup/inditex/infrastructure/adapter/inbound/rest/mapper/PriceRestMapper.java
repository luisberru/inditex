package com.bcncgroup.inditex.infrastructure.adapter.inbound.rest.mapper;

import com.bcncgroup.inditex.infrastructure.adapter.inbound.rest.dto.PriceResponseDto;
import com.bcncgroup.inditex.domain.model.Price;

public class PriceRestMapper {

    private PriceRestMapper() {
        // Evita la instanciación
    }
    /**
     * Convierte un objeto Price a un objeto PriceResponseDto.
     *
     * @param price el objeto Price a convertir
     * @return un objeto PriceResponseDto con los datos del objeto Price
     */
    public static PriceResponseDto toDto(Price price) {
        return new PriceResponseDto(
                price.productId(),
                price.brandId(),
                price.priceList(),
                price.startDate(),
                price.endDate(),
                price.amount(),
                price.currency().getCurrencyCode());
    }
}
