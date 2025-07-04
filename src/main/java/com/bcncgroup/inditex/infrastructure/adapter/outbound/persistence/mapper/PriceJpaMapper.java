package com.bcncgroup.inditex.infrastructure.adapter.outbound.persistence.mapper;

import com.bcncgroup.inditex.infrastructure.adapter.outbound.persistence.entity.PriceEntity;
import com.bcncgroup.inditex.domain.model.Price;

import java.util.Currency;

public class PriceJpaMapper {

    private PriceJpaMapper() {
        // Evita la instanciación
    }
    /**
     * Convierte un objeto Price a PriceEntity.
     *
     * @param e El objeto Price a convertir.
     * @return Un objeto PriceEntity con los datos del objeto Price.
     */
    public static Price toDomain(PriceEntity e) {
        return new Price(
                e.getId(),
                e.getBrandId(),
                e.getStartDate(),
                e.getEndDate(),
                e.getPriceList(),
                e.getProductId(),
                e.getPriority(),
                e.getPrice(),
                Currency.getInstance(e.getCurr()));
    }
}
